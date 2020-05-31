# using the SimpleGraphics library
from SimpleGraphics import *

# tell SimpleGraphics to only draw when I use the update() function
setAutoUpdate(False)


# use the random library to generate random numbers
import random
import math

# size of the circles drawn
diameter = 15

resize(600, 300)


##
# returns a vaid color based on the input coordinates
#
# @param x is an x-coordinate 
# @param y is a y-coordinate 
# @return a colour based on the input x,y values for the given flag
##

def define_colour(x,y):
    targetPoint = math.sqrt(float(getHeight()/3 * getHeight()/3)/4)
    
    # for left
    p1x = 0.0
    p1y = targetPoint
    
    p2x = 0.0
    p2y = float(getHeight() - targetPoint)
    
    p3x = float((getWidth()/2) - (targetPoint/2))
    p3y = float(getHeight()/2)
    
    #u=upper triangle
    #p=lower triangle , I am going to make this left
    
    # for down
    d1x, d1y, d2x, d2y, d3x, d3y = targetPoint, float(getHeight()), float(getWidth()/2), float(getHeight()/2 + targetPoint/2), float(getWidth() - targetPoint), float(getHeight())
    
    # for up
    u1x = targetPoint
    u1y = 0.0
    
    u2x = float(getWidth()- targetPoint)
    u2y = 0.0
    
    u3x = float(getWidth()/2)
    u3y = float((getHeight()/2) - targetPoint/2)
    
    # for right
    r1x, r1y, r2x, r2y, r3x, r3y = float(getWidth()), targetPoint, float(getWidth()), float(getHeight()-targetPoint), float((getWidth()/2) + (targetPoint/2)), float(getHeight()/2)
    
    alphaL = float(((p2y - p3y)*(x - p3x) + (p3x - p2x)*(y - p3y)) /
        ((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y)));
    betaL = float(((p3y - p1y)*(x - p3x) + (p1x - p3x)*(y - p3y)) /
           ((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y)));
    gammaL = float(1.00 - alphaL - betaL);
    
    alphaDown = float(((d2y - d3y)*(x - d3x) + (d3x - d2x)*(y - d3y)) /
        ((d2y - d3y)*(d1x - d3x) + (d3x - d2x)*(d1y - d3y)));
    betaDown = float(((d3y - d1y)*(x - d3x) + (d1x - d3x)*(y - d3y)) /
           ((d2y - d3y)*(d1x - d3x) + (d3x - d2x)*(d1y - d3y)));
    gammaDown = float(1.00 - alphaDown - betaDown);
    
    
    alphaUp = float(((u2y - u3y)*(x - u3x) + (u3x - u2x)*(y - u3y)) /
        ((u2y - u3y)*(u1x - u3x) + (u3x - u2x)*(u1y - u3y)));
    betaUp = float(((u3y - u1y)*(x - u3x) + (u1x - u3x)*(y - u3y)) /
           ((u2y - u3y)*(u1x - u3x) + (u3x - u2x)*(u1y - u3y)));
    gammaUp = float(1.00 - alphaUp - betaUp);

    alphaR = float(((r2y - r3y)*(x - r3x) + (r3x - r2x)*(y - r3y)) /
        ((r2y - r3y)*(r1x - r3x) + (r3x - r2x)*(r1y - r3y)));
    betaR = float(((r3y - r1y)*(x - r3x) + (r1x - r3x)*(y - r3y)) /
           ((r2y - r3y)*(r1x - r3x) + (r3x - r2x)*(r1y - r3y)));
    gammaR = float(1.00 - alphaR - betaR);
    
    if(((alphaL>0.0) and (betaL>0.0) and (gammaL>0.0))or ((alphaR>0.0) and (betaR>0.0) and (gammaR>0.0))):
        return 'black'
    elif(((alphaDown>0.0) and (betaDown>0.0) and (gammaDown>0.0))or ((alphaUp>0.0) and (betaUp>0.0) and (gammaUp>0.0))):
        return 'green'
    else:
        return 'yellow'


######################################################################
#
# Do NOT change anything below this line
#
######################################################################

# repeat until window is closed
while not closed():
  for i in range(500):
    # generate random x and y values 
    x = random.randint(0, getWidth())
    y = random.randint(0, getHeight())

    # set colour for current circle
    setFill( define_colour(x,y) )

    # draw the current circle
    ellipse(x, y, diameter, diameter)

  update()
