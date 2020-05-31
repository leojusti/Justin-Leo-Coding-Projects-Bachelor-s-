#Q1

import random

x = random.randint(0,100)

tries = 0

print ('Welcome to the guessing game')
print ('------------------------------------------------')
print ('I have chosen a secret number')

userOption = input('Take a guess: ')

while (userOption!=-1):
    tries=tries+1
    
    if(userOption==x):
        print ('That is right!')
        print("It took you "+str(tries)+" guesses.")
        break
        
    else:
        if(userOption>x):
            userOption = input("Sorry, that is too high. Guess again : ")
            
        else:
            userOption = input("Sorry, that is too low. Guess again : ")
            
if(userOption==-1):
    print("Better luck next time.")  
    print('You tried '+ str(tries)+' guesses.')