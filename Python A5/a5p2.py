## a5p2.py
## 
## text drawing program












#def main():
#    
#    # your main method that drives your program
#    # - asks for user input
#    # use print() in the for loop for x instead of (\n) to space the lines properly
#    # (n, end="") breaks each line
#    n = int(input("enter an integer between 1 and 10: "))
#    for x in range (1,n+1):
#        for y in range (1, n+1):
#            print (n, end="")
#        print ()
#        
#    # - draws the five shapes based on the user input
#    pass


def main():
    n = int(input("enter an integer between 1 and 10: "))
    print("This prints first part of Q2")
    for x in range (1,n+1):
        for y in range (1, n+1):
            print (n, end="")
        print ()
    print("This prints 2nd part of Q2")
    for x in range (1,n+1):
        for y in range (1, x+1):
            print (n, end="")
        print ()
    print("This prints for 3rd part of Q2")   
    for x in range (1,n+1):
        for y in range (1, n+1-x):
            print (" ", end="")
        for y in range (n-x,n):
            print (n, end="")
        print ()
    print("This prints for 4th part of Q2")
    for x in range (1,n+1):
        for y in range (1, x):
            print (" ", end="")
        for y in range (x,n+1):
            print (n, end="")
        print ()
    print("This prints for 5th part of Q2")
    for x in range (1,n+1):
        for y in range (0, n-x+1):
            print (n, end="")
        print ()
        
    




##
# Leave this at the bottom of your file
##


##
# This is statement checks if there is a main method define
# in the file and calls the method if there is one.
# This line is needed so that you can run your program
# from a shell with
#
# python a5p2.py
#
# Do not change this line.
##

if __name__ == "__main__":
    # execute only if run as a script
    main()