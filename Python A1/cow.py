# cow.py

#Colors used
#brown
#white
#black
#almond

#shapes used
#ellipse
#blob
#rect
#pieSlice

# using the SimpleGraphics library
from SimpleGraphics import *

# window for the whole diagram
setOutline(0, 0, 0)
setFill(0, 191, 255)
rect (0,0, 800, 600)

#two arcs
setOutline(0, 0, 0)
setFill(50, 205, 50)
blob(0, 270, 0,270, 500,339, 650, 370, 800, 420, 800,420, 800, 600, 800, 600, 0, 600, 0, 600, 0, 270, 0, 270)

#2nd blob
setOutline(0, 0, 0)
setFill(50, 205, 50)
blob(500, 339,650, 275, 800, 250, 800, 250, 800, 420, 800, 420, 650, 370, 500, 339, 500, 339)

def drawCow(x, y):
    print("cowx is :", x)
    print("cowy is :", y)

    #converting the input to int
    x=int(x)
    y=int(y)
    
    #tail
    setOutline(0, 0, 0)
    setFill(255, 255, 255)
    rect(x, y,10, 100)
    
    #tail to stomach connector
    setOutline(0, 0, 0)
    setFill(255, 255, 255)
    rect(x+10, y, 10, 20)
    
    #stomach
    setOutline(0, 0, 0)
    setFill(255, 255, 255)
    rect(x+20, y, 300, 200)

    #inside the stomach1
    setOutline(0, 0, 0)
    setFill(139,69,19)
    ellipse(x+30, y+10, 20, 100)
    
    #inside the stomach2
    setOutline(0, 0, 0)
    setFill(139,69,19)
    ellipse(x+100, y+50, 100, 50)
    
    #ears
    setOutline(0, 0, 0)
    setFill(255, 255, 255)
    ellipse(x+240, y-100, 80,30)
    
    setOutline(0, 0, 0)
    setFill(255, 255, 255)
    ellipse(x+330, y-90, 80,30)
    
    #head
    setOutline(0, 0, 0)
    setFill(255, 255, 255)
    blob(x+280, y-150, x+280, y-150, 
         x+370, y-150, x+370, y-150, 
         x+345, y+100, x+345, y+100, 
         x+300, y+100, x+300, y+100, 
         x+280, y-150, x+280, y-150)
    
    #eyes
    setOutline(0, 0, 0)
    setFill(0, 0, 0)
    pieSlice(x+290, y-120, 20, 20, 0, 90)
    
    setOutline(0, 0, 0)
    setFill(0, 0, 0)
    pieSlice(x+340, y-120, 20, 20, 90, 90)
    
    #mouth
    setOutline(0, 0, 0)
    setFill(255,235,205)
    rect(x+305, y,40, 40)
    
    #legs
    setOutline(0, 0, 0)
    setFill(255, 255, 255)
    rect(x+20, y+200, 20, 100)

    setOutline(0, 0, 0)
    setFill(255, 255, 255)
    rect(x+50, y+200, 20, 80)
    
    setOutline(0, 0, 0)
    setFill(255, 255, 255)
    rect(x+250, y+200, 20, 100)
    
    setOutline(0, 0, 0)
    setFill(255, 255, 255)
    rect(x+280, y+200, 20, 80)
    
    print("cow drawn")

# assume a default starting point for the cow   
x=100
y= 350

# get cow location
x = input("enter your x coordinate of the cow :")
y = input("enter your y coordinate of the cow :")
    
# draw cow
drawCow(x, y)

#get cow name
cowName = input("Enter the name of the cow :")

# print the cow name
setFont("Arial", 18)
text(600,500, "Sally the cow")
    
# wait for user to hit enter before ending program
input("press enter to end")

# close the graphics window
close()