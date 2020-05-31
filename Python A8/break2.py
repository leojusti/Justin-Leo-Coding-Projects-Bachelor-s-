##
## Arkenoid/Breakout Version 1
##
## You can use this as a starting point for your
## Assignment 8 extension to the game

from random import randint, random
from time import sleep


from SimpleGraphics import *
setAutoUpdate(False)

## define the graphics window
setWindowTitle("BreakOut 2016!")
w,h = 600,425
bottom = 400
resize(w,h)
background('midnightblue')
setFont("Arial", "16", "bold")

gap = 3

## paddle class
class Paddle:
    """ A Paddle represents a paddle in the game """
    length,width,help_width,height = w/7,w/7,2*w/7,h/20
    help = False
    x,y = 10, bottom-height-gap
    dx,dy = 1,0
    c = 'green'

# ball class
class Ball: 
    """ A Ball represents a ball in the game """
    size  = 20
    x, y  = w/2,bottom/3
    dx,dy = random()*1-.2, random()*1+0.5
    c = 'yellow'
    count = 3

class Block():
    w = getWidth()
    h = 25
    cBefore = 'red'
    cAfter = 'midnightblue'
    c=cBefore
    bX = 0
    bY = 0
    value = 10 #score
    hitsRemaining  = 3
    oBefore = 'black'  #outline before
    o = oBefore
    oAfter = cAfter
    broken = False
    
    def setY(self, n):
        self.bY = n * self.h
    
def drawThings(paddle, ball, block1, block2, block3):
    # paddle
    setColor(paddle.c)
    rect(paddle.x, paddle.y, paddle.length, paddle.height)
    # ball
    setColor(ball.c)
    ellipse(ball.x-ball.size//2, ball.y-ball.size//2, ball.size, ball.size)

    #blocks
    renderBlock(block1)
    renderBlock(block2)
    renderBlock(block3)

def renderBlock(block):
    setColor(block.c)
    setOutline(block.o)
    rect(block.bX, block.bY, block.w, block.h)
    
def  drawScore(ball, score, block1, block2, block3):
    global w, h
    ## update score
    setColor('black')
    text(20,h-12, "Balls left " + str(ball.count), 'w')
    text(w-textWidth("Score = XXXXXXXXXX"), h-12, "Score = " + str(score), 'w')
    
    hitsRemaining = 0
    if(block3.broken == False and (block2.broken == False) and (block1.broken == False) ):
        hitsRemaining = block3.hitsRemaining
    elif(block3.broken == True and (block2.broken == False) and (block1.broken == False) ):
        hitsRemaining = block2.hitsRemaining
    else:
        hitsRemaining = block1.hitsRemaining

    text((w/2)-(w/6), h-12, "Hits Remaining: " + str(hitsRemaining), 'w')
    
def updateThings(paddle, ball, keys, block1, block2, block3, score):
    global w, h, bottom

    ## update paddle

    # check if help key is pressed
    if 'h' in keys and paddle.help == False:
        paddle.help = True
        paddle.length = paddle.help_width
        paddle.x = paddle.x - (paddle.help_width-paddle.width)/2
    elif 'h' not in keys and paddle.help == True:
        paddle.help = False
        paddle.length = paddle.width
        paddle.x = paddle.x + (paddle.help_width-paddle.width)/2

    # move left and right	
    if "Left" in keys and "Right" not in keys:
        paddle.x = max(paddle.x - paddle.dx,gap)
    elif "Right" in keys and "Left" not in keys:
        paddle.x = min(paddle.x + paddle.dx, w-paddle.length-gap)

    if paddle.x <= 0 :
        paddle.x = 0
    elif paddle.x >= w - paddle.width:
        paddle.x = w - paddle.width
        
    ## update ball
    ball.x += ball.dx
    ball.y += ball.dy
    if ball.x <= ball.size/2:
        ball.x = ball.size/2
        ball.dx = -ball.dx
    elif ball.x >= w-ball.size/2:
        ball.x = w-ball.size/2
        ball.dx = -ball.dx

    if ball.y <= 0:
        ball.y = 0
        ball.dy = -ball.dy

    elif (ball.y + ball.size/2 >= paddle.y and paddle.x <= ball.x + ball.size/2 <= paddle.x + paddle.length):
        ball.y = paddle.y - ball.size
        ball.dy = -ball.dy
    elif ball.y + ball.size/2 >= h:
        ball.count -= 1
        ball.x, ball.y  = w/2,h/3
        ball.dx,ball.dy = random()*1-.2, random()*1+0.5

    ## allow for user to change speed of ball
    ## this is just to help test the game (we can slow the up/down motion up)
    if "Down" in keys:
        ball.dy = 0.95*ball.dy
    elif "Up" in keys:
        ball.dy = 1.05*ball.dy
    
    #check if the currentPosition of the ball (h + radius) are equal to block area, then change the color of the block to invisible, increase the score, let the ball travel through the blocks (but may be not increment scores all at once)
    
    didBallHitBlock = didCollide(ball, block3)
    if(didBallHitBlock):
        ball.dy = -ball.dy 
        if(block3.hitsRemaining == 0):
            score = score + block3.value
        
    didBallHitBlock = didCollide(ball, block2)
    if(didBallHitBlock):
        ball.dy = -ball.dy 
        if(block2.hitsRemaining == 0):
            score = score + block2.value
        
    didBallHitBlock = didCollide(ball, block1)
    if(didBallHitBlock):
        ball.dy = -ball.dy 
        if(block1.hitsRemaining == 0):
            score = score + block1.value

    return score

def didCollide(ball, block):
    ballRadius = ball.size/2
    if(((ball.y - ballRadius)  <= (block.bY + block.h)) and (block.broken == False)):
        block.hitsRemaining = block.hitsRemaining-1
        if(block.hitsRemaining == 0):
            block.c = block.cAfter
            block.o = block.oAfter
            block.broken = True
            block.h = 0
        return True
    else:
        return False

def main():
    # create a paddle and a ball for the game
    paddle = Paddle()
    ball = Ball()	
    
    block1 = Block()
    Block.setY(block1, 0)
    
    block2 = Block()
    Block.setY(block2, 1)
    
    block3 = Block()
    Block.setY(block3, 2)

    score = 0

    ## main loop of the game	
    while not closed():
        clear()

        # check where we are in the game
        if ball.count == 0:
            # game is over
            text(w/2,h/2,"Game Over")

        else:
            # draw the elements in the game
            drawThings(paddle, ball, block1, block2, block3)

            # get the keys currently held
            keys = getHeldKeys()

            # update ball and paddle, checking for collisions
            score = updateThings(paddle, ball, keys, block1, block2, block3, score)

            #sleep(0.01)

        # always draw text box at bottom of screen
        setColor('azure1')
        rect(0,bottom,w,h-bottom)
        drawScore(ball, score, block1, block2, block3)


if __name__ == "__main__":
    main()