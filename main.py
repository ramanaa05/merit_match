from fastapi import FastAPI, HTTPException, Depends, status
from pydantic import BaseModel
from typing import Annotated
import models
from database import engine, SessionLocal
from sqlalchemy.orm import Session
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy import text
from passlib.context import CryptContext

app = FastAPI()
models.Base.metadata.create_all(bind = engine)
pwd_context = CryptContext(schemes=["bcrypt"], deprecated="auto")

origins = [
    "http://localhost:3000",  # React or other frontend running on localhost
    "http://localhost:8000",  # Your backend running on localhost
    # Add other origins as needed
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

class UserBase(BaseModel):
    username: str
    password: str
    email: str
    karmaPoints: int
    rating: float
    ratingTotal: int

class TaskBase(BaseModel):
    id: int
    username: str
    task: str
    karmaPoints: int
    reserved: str

class HistoryBase(BaseModel):
    id: int
    username: str
    task: str
    karmaPoints: int
    reserved: str
    status: str

class ApproveBase(BaseModel):
    id: int
    approved: bool

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()
db_dependency = Annotated[Session, Depends(get_db)]
        
@app.post("/users/", status_code = status.HTTP_201_CREATED)
async def create_user(user: UserBase, db: db_dependency):
    user.rating = 0
    user.ratingTotal = 0
    hashedPassword = pwd_context.hash(user.password)
    user.password = hashedPassword
    db_user = models.User(**user.dict())
    db.add(db_user)
    db.commit()
    return user


@app.post("/users/rate/", status_code = status.HTTP_201_CREATED)
async def rate_user(user: UserBase, db: db_dependency):
    user_db = db.query(models.User).filter(models.User.username == user.username).first()
    user_db.rating = user.rating
    user_db.ratingTotal = user_db.ratingTotal+1

    db.commit()
    db.refresh(user_db)
    return user

@app.get("/user/rating/", status_code = status.HTTP_200_OK)
async def get_rating(username: str, db: db_dependency):
    user = db.query(models.User).filter(models.User.username == username).first()
    return {"rating": user.rating}

@app.post("/users/authentication", status_code = status.HTTP_200_OK)
async def get_user(user: UserBase, db: db_dependency):
    username = user.username
    password = user.password
    user_db = db.query(models.User).filter(models.User.username == username).first()
    

    if user_db is None:
        return {"username": "404 error: user not found"}
    else:
        check = pwd_context.verify(password, user_db.password)
        if not check:
            return {"username": "error code -1: incorrect password"}
        
    return user_db

@app.post("/tasks/", status_code = status.HTTP_201_CREATED)
async def create_task(task: TaskBase, db: db_dependency):
    db_task = db.query(models.Tasks).filter(models.Tasks.id == task.id).first() 
    if db_task is None:
        db_task = models.Tasks(**task.dict())
        history = HistoryBase(
            id=task.id,
            username=task.username,
            task = task.task,
            karmaPoints = task.karmaPoints,
            reserved = task.reserved,
            status = "posted")
        db_history = models.TaskHistory(**history.dict())
        db.add(db_history)
        db.add(db_task)
        db.commit()
    else:
        db_history = db.query(models.TaskHistory).filter(models.TaskHistory.id == task.id).first() 
        db_task.task = task.task
        db_task.karmaPoints = task.karmaPoints
        db_history.task = task.task
        db_history.karmaPoints = task.karmaPoints
        db.commit()
        db.refresh(db_task)
        db.refresh(db_history)
    
    return task

@app.get("/tasks/", status_code = status.HTTP_200_OK)
async def get_tasks(username: str, db: db_dependency):
    tasks = db.query(models.Tasks).filter(models.Tasks.username != username).all()
    return {"tasks": tasks}

@app.get("/tasks/all/", status_code = status.HTTP_200_OK)
async def get_tasks_all(db: db_dependency):
    tasks = db.query(models.Tasks).all()
    return {"tasks": tasks}

@app.get("/tasks/history/", status_code = status.HTTP_200_OK)
async def get_history(db: db_dependency):
    tasks = db.query(models.TaskHistory).all()
    return {"history": tasks}

@app.get("/tasks/reserved/", status_code = status.HTTP_200_OK)
async def get_tasks_reserved(username: str, db: db_dependency):
    tasks = db.query(models.Tasks).filter(models.Tasks.reserved == username).all()
    return {"tasks": tasks}

@app.post("/tasks/reserve/", status_code = status.HTTP_201_CREATED)
async def reserve_task(task: TaskBase, db: db_dependency):
    task_to_reserve = db.query(models.Tasks).filter(models.Tasks.id == task.id).first()
    status_change = db.query(models.TaskHistory).filter(models.TaskHistory.id == task.id).first()

    if task_to_reserve is None:
        raise HTTPException(status_code=404, detail="Task not found")

    task_to_reserve.reserved = task.reserved
    status_change.reserved = task.reserved
    status_change.status = "reserved"
    db.commit()
    db.refresh(task_to_reserve)
    db.refresh(status_change)
    return task_to_reserve

@app.post("/tasks/approve/", status_code = status.HTTP_201_CREATED)
async def approve_task(approvalStatus: ApproveBase, db: db_dependency):
    task_to_approve = db.query(models.Approve).filter(models.Approve.id == approvalStatus.id).first()

    if task_to_approve is None:
        task_to_approve = models.Approve(**approvalStatus.dict())
        db.add(task_to_approve)
        db.commit()
    else:
        task_to_approve.approved = approvalStatus.approved
        db.commit()
        db.refresh(task_to_approve)
    return task_to_approve

@app.get("/tasks/approve/all/", status_code = status.HTTP_200_OK)
async def get_approval_status(db: db_dependency):
    tasks = db.query(models.Approve).all()
    return {"status": tasks}

@app.post("/tasks/all/delete", status_code = status.HTTP_200_OK)
async def delete_task(delete: TaskBase, db: db_dependency):
    task = db.query(models.Tasks).filter(models.Tasks.id == delete.id).first()
    if task is None:
        raise HTTPException(status_code=404, detail="Task not found")
    db.delete(task)
    db.commit()
    return task

@app.post("/tasks/approve/delete", status_code = status.HTTP_200_OK)
async def delete_approval(delete: ApproveBase, db: db_dependency):
    task = db.query(models.Approve).filter(models.Approve.id == delete.id).first()
    if task is None:
        raise HTTPException(status_code=404, detail="Task not found")
    db.delete(task)
    db.commit()
    return task

@app.post("/tasks/transaction/", status_code = status.HTTP_201_CREATED)
async def reserve_task(task: TaskBase, db: db_dependency):
    task_db = db.query(models.Tasks).filter(models.Tasks.id == task.id).first()
    postedBy = task.username
    doneBy = task.reserved
    giver = db.query(models.User).filter(models.User.username == postedBy).first()
    reciever = db.query(models.User).filter(models.User.username == doneBy).first()
    status_change = db.query(models.TaskHistory).filter(models.TaskHistory.id == task.id).first()
    
    
    status_change.status = "completed"
    giver.karmaPoints = giver.karmaPoints - task.karmaPoints
    reciever.karmaPoints = reciever.karmaPoints + task.karmaPoints

    if task_db is None:
        raise HTTPException(status_code=404, detail="Task not found")
    db.commit()
    db.refresh(giver)
    db.refresh(reciever)
    return task_db