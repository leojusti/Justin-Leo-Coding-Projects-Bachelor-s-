#Q3

# flag2.py
# using the SimpleGraphics library
from SimpleGraphics import *
# use the randint function from the random library
# to generate random numbers
from random import randint
# size of the circles to draw
diameter = 15
# repeat until window is closed
while not closed():
    # generate random x and y values
    x = randint(0, getWidth())
    y = randint(0,getHeight())
    ##
    #
    # Add code here to change the fill colour of the circle to be
    # drawn based on the x and y coordinates.
    # This is the only place you should be modifying the program.
    
    if (x<= getWidth()/3):
        setFill(0,128,0)
    else:
        if (y<= getHeight()/2):
            setFill(255,255,0)
        else:
            setFill(255,0,0)
    #
    ##
    # draw the current circle
    ellipse(x, y, diameter, diameter)