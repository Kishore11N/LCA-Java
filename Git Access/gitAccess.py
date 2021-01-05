from flask import Flask, flash, redirect, render_template, request, session, abort,send_from_directory,send_file,jsonify
from github import Github
from pprint import pprint
import json
import csv


# Validate function to ensure authorised person is viewing the repositories and that the inputs are valid
def validate():

    validDetails = False
    
    while(validDetails == False):

        validToken = False
        validUser = False
        validRepo = False
    
        while (validToken == False):         
            try:      
                access_token = input("Please enter your access token : ")  
                g = Github(access_token)
                g.get_user("esjmb")
                validToken = True
            except:
                print("Access token is invalid, please try again")

        while (validUser == False):          
            try:
                user_github = input("Please enter user you would like to view : ")
                user = g.get_user(user_github)
                validUser = True             
            except:
                print("User does not exist, please try again")

        while (validRepo == False):           
            try:     
               repoName = input("Please enter the name of the repository : ") 
               repo = user.get_repo(repoName)   
               validRepo = True               
            except:
                print("Repository name is invalid, please try again")

        if validUser == True & validRepo == True :
            if validToken ==True:
                validDetails = True

    return [validDetails,access_token,user_github,repoName]

#This function gets the relevant information using the inputs provided by the user
def getAllRepos(repo):
  numOfRepos = []
  contributor_names = []
  no0fContributions = []
  personCounter = 0
  repoCounter = 0
  contributors = repo.get_contributors()
  
  for contributor in contributors:
    personCounter += 1
    print("Countributor number : " + str(personCounter))
    contributorLogin = str(contributor.login)
    contributionTime = str(contributor.contributions)
    user1 = g.get_user(contributorLogin)
    
    for repo in user1.get_repos():
          repoCounter += 1
    
    contributor_names .append(contributorLogin)
    no0fContributions.append(contributionTime)
    numOfRepos.append(str(repoCounter))
    
#Returns a list containing three lists regarding information about the contributors of a repo
  return [contributor_names,no0fContributions,numOfRepos]


valid = validate()
isValid = valid[0]
access_token = valid[1]
user_github = valid[2]
repoName = valid[3]


#If all inputs are validated, then collect required information
if isValid == True:
    g = Github(access_token)
    user = g.get_user(user_github)
    for repo in user.get_repos():
        if(repo.name == repoName):
           info = getAllRepos(repo)
           contributors = info[0]
           commits = info[1]
           experience = info[2]       
           data = []

    # Create a dictionary of values
    for cr, cn, rn in zip(contributors, commits, experience):
        personInfo = {'name': cr, 'commits': cn, 'repos': rn}
        data.append(personInfo)

        keys = data[0].keys()

# Write information to a csv file
with open('people.csv', 'w', newline='')  as output_file:
    dict_writer = csv.DictWriter(output_file, keys)
    dict_writer.writeheader()
    dict_writer.writerows(data)

# Reading the csv file that was created
with open('people.csv', newline='') as File:  
    reader = csv.reader(File)
    for row in reader:
        print(row)

