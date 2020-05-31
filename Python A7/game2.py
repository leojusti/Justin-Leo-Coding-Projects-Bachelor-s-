num = 4

##
## walking through balls
##

from SimpleGraphics import *
from time import sleep
from random import randint, random

# only draw things when we say to do it (update(), clear())
setAutoUpdate(False)

# we can do multiple assignments on a single line!!
width,height = 600,600

resize(width,height)
background('midnightblue')

# player has some properties
i,j = width*(3/4), height*(3/4)
dx,dy = 0,0
c = 'magenta'
size = 30
paddleWidth = 100
paddleHeight = 20
x,y = (width/2)-(paddleWidth/2), height/2
out = 0   # count how many times we go out of bounds

# ball
ball_x, ball_y = randint(50, width-50), 3
ball_size = 13             # size of ball
ball_c = 'yellow'          # ball's colour
ball_dx = random()*5-2.5   # x-direction speed
ball_dy = random()*5-2.5 # y-direction speed
colors = ['yellow'] 
acount = 0

while not closed():
    ##
    ## render the scene
    ##
    clear()

    # draw the player
    setColor(c)
    rect(x,j,paddleWidth,paddleHeight)

    # draw the ball //asteriod becomes the ball and bounces
    setColor(ball_c)
    ellipse(ball_x, ball_y, ball_size, ball_size)

    # some messages for the game
    setColor('white')
    text(75,height-40, "out of bounds " + str(out))
    text(75,height-20, "count is " + str(acount))

    # move the player
    keys = getHeldKeys()
    step = 2
#	
    if "Left" in keys:
        if(x>0 ):
            dx = dx - step

    elif "Right" in keys:
        if(dx+step<=(getWidth()-paddleWidth)):
            dx = dx + step
    
    if(x+dx<=(getWidth()-paddleWidth) and x+dx>=0):
        x=x+dx

    if(x>=0 or x <=(getWidth()-paddleWidth)):
        dx=0

    ball_x = ball_x + ball_dx
    ball_y = ball_y + ball_dy


    if ball_x > width-ball_size/2: ball_x = 0
    if ball_x < -ball_size/2 : ball_x = width -ball_size/2

    if ball_y > height-ball_size/2: ball_y = 0
    if ball_y < -ball_size/2 : ball_y = height - ball_size/2

    # slow things down
    # sleep(.1)