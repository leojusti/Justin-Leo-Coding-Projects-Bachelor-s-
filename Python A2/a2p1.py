#Grade Calc Question 1

#allocated weights for each grading items
assignmentWeight = 0.3
midTermWeight = 0.3
tutorialWeight = 0.1
finalExamWeight = 0.3

#create a local variable for the final grade variable
finalGrade = 0.0

#get all the user inputs and store them in each variables
assignmentGrade = float(input('ASSIGNMENT grade: '))
tutorialGrade = float(input('TUTORIAL grade: '))
midTermGrade = float(input('MIDTERM grade: '))
finalExamGrade = float(input('FINALEXAM grade: '))

#sum of written exams
weightedExamGrade = midTermGrade + finalExamGrade

#now check if the midTerm and finalExam grade are greater than pass percent
if(weightedExamGrade > 99):
    finalGrade =    (assignmentWeight * assignmentGrade) + (midTermWeight * midTermGrade) + (tutorialWeight * tutorialGrade) + (finalExamWeight * finalExamGrade)
else:
   finalGrade = weightedExamGrade/2

print ("Final Grade is:")
print (finalGrade)