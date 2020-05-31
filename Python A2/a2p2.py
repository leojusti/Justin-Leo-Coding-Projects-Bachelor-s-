#Q2

print ("Choose a letter from 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' ")

answer1 = input(" Is the letter >= 'e'").lower()


if(answer1 == "yes"):
    answer2 = input("Is the letter >=g").lower()
    if(answer2 == "yes"):
        answer3 = input("Is the letter g??").lower()
        if(answer3=="yes"):
            print ("The letter you thought is 'g'")
        else:
            print ("The letter you thought is 'h'")
    else:
        answer3 = input("Is the letter e??").lower()
        if(answer3=="yes"):
            print ("The letter you thought is 'e'")
        else:
            print ("The letter you thought is 'f'")
else:
    answer2 = input("Is the letter >=c").lower()
    if(answer2 == "yes"):
        answer3 = input("Is the letter c??").lower()
        if(answer3=="yes"):
            print ("The letter you thought is 'c'")
        else:
            print ("The letter you thought is 'd'")
    else:
        answer3 = input("Is the letter a??").lower()
        if(answer3=="yes"):
            print ("The letter you thought is 'a'")
        else:
            print ("The letter you thought is 'b'")