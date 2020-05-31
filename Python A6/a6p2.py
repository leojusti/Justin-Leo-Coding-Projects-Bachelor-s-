##
## a6p2.py
##

import random

def main():
    
    x = random.randint(100,200)

    validTries = 0
    invalidTries = 0
    isUserOptionInt = False 
    
    userOption = raw_input('Please enter a number between 100 and 200: ')

    while (userOption):
        userOptionInt = 0
        
        try:
            userOptionInt = int(userOption)
            isUserOptionInt = True
            
        except ValueError:
            isUserOptionInt = False
        
        if (isUserOptionInt):
            validTries = validTries + 1
        else:
            invalidTries = invalidTries + 1

        if(userOptionInt==x):
            print('You entered ' + str(validTries) + ' numbers and ' + str(invalidTries) + ' non-numbers to get it right.')
            break
            
        elif (not (isUserOptionInt)):
            userOption = raw_input('That is not a number. Try again :')

        else:
            if(userOptionInt>x):
                userOption = raw_input("That number is too high. Try again : ")

            else:
                userOption = raw_input("That number is too low. Try again : ")

###############################################################
## DO NOT CHANGE THIS! LEAVE IT AT THE BOTTOM OF YOUR FILE
## calls the main function when this file is run with Python
##
## python stats.py
##
###############################################################
if __name__ == "__main__":
	main()
	