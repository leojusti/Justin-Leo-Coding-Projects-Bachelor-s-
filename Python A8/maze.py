""" 
Assignment 8 - problem 1
maze.py
"""

from time import sleep 
from SimpleGraphics import *
# use the random library to generate random numbers
import random
import math

setAutoUpdate(False)
resize(600,400)


# define the maze
maze = [[4,0,0,0,0,1],
        [1,1,0,0,0,1],
        [0,0,0,1,0,2],        
        [0,1,1,0,0,1],
        [0,1,3,0,1,0],              
        [0,1,1,0,0,5]]

#Yellow is treasure, orange is theif 

x=1
score=0

def draw(maze):
    """ 
    Draws the maze given as input
    The input is a two-dimensional list of integers
    """

    # width and height of the maze
    w = len(maze[0])  # width is number of columns
    h = len(maze)     # height is number of rows
    
    sizeWidth = getWidth()/w
    sizeHeight = getHeight()/h

    for row in range(h):
        for col in range(w):
            if maze[row][col] == 1: #empty cell
                setColor('black')
            elif maze[row][col] == 4:  #start
                setColor('green')
            elif maze[row][col] == 5: # exit
                setColor('red')
            elif maze[row][col] == 2:  #start
                setColor('yellow')
            elif maze[row][col] == 3: # exit
                setColor('orange')
            else:
                setColor('white')

            rect(sizeWidth*col, sizeHeight*row, sizeWidth, sizeHeight)   
   
def resetPlayerOnScreen(pR, pC):
    w = len(maze[0])  # width is number of columns
    h = len(maze)     # height is number of rows
    
    sizeWidth = getWidth()/w
    sizeHeight = getHeight()/h

    setColor('#0000FF')
    ellipse(pC*sizeWidth, pR*sizeHeight, sizeWidth, sizeHeight)

#Player location settings
def getRandomCol():
    x = random.randint(0,4)
    return x

def getRandomRow():
    x = random.randint(0,5)
    if(x==1):
        return getRandomRow()
    else:
        return x

def getRandRowOrCol():
    x = random.randint(0,1)
    return x
    
while not closed():
    if(x==1):
        pR = 0
        pC = 0 

        randCol = getRandomCol()
        randRow = getRandomRow()
        rowOrCol = getRandRowOrCol()
        
        if(rowOrCol == 0):
            pR = randRow
            pC=0
        else:
            pR = 0
            pC = randCol

        maze[0][0] = 0
        maze[pR][pC] = 4
        resetPlayerOnScreen(pR, pC)
        x=x+1
    #if ended
    
    clear()
    draw(maze)
    
    keys = getHeldKeys()
    if "Left" in keys:
        if(pC>=1 and maze[pR][pC-1] != 1):
            pC=pC-1
    elif "Right" in keys:
        if(pC<=4 and maze[pR][pC+1] != 1):
            pC=pC+1
    elif "Up" in keys:
        if(pR>=1 and maze[pR-1][pC] != 1):
            pR=pR-1
    elif "Down" in keys:
        if(pR<=4 and maze[pR+1][pC] != 1):
            pR=pR+1
            
    resetPlayerOnScreen(pR, pC)
    
    if(pR==2 and pC==5):
        maze[pR][pC]=0 # meaning player hit the treasure
        score=1
    if(pR==4 and pC==2): # meaning player hit the thief
        score=0
    
    if(pR==5 and pC==5):
        print("You won")
        print("Your score is "+str(score))
        clear()
        text(getWidth()/2,getHeight()/2, "Game Won, Score is: " + str(score))
        break
        
    sleep(0.05)