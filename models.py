from sqlalchemy import Boolean, Column, Integer, String, Float
from database import Base

class User(Base):
	__tablename__ = "users"

	username = Column(String(300), primary_key = True, index = True)
	password = Column(String(300))
	email = Column(String(300))
	karmaPoints = Column(Integer)
	rating = Column(Float)

class Tasks(Base):
	__tablename__ = "tasks"

	id = Column(Integer, primary_key = True)
	task = Column(String(3000))
	username = Column(String(3000))
	karmaPoints = Column(Integer)
	reserved = Column(String(3000))

class TaskHistory(Base):
	__tablename__ = "history"

	id = Column(Integer, primary_key = True)
	task = Column(String(3000))
	username = Column(String(3000))
	karmaPoints = Column(Integer)
	reserved = Column(String(3000))
	status = Column(String(400))

class Approve(Base):
	__tablename__ = "approve"
	id = Column(Integer, primary_key = True)
	approved = Column(Boolean)