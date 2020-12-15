from github import Github
from pprint import pprint
import json

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
    
  #returns a list containing three lists regarding information about committers of a repo
  return [contributor_names,no0fContributions,numOfRepos]


valid = validate()
isValid = valid[0]
access_token = valid[1]
user_github = valid[2]
repoName = valid[3]


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

           for cr, cn, rn in zip(contributors, commits, experience):
            personInfo = {'person': {'login': cr, 'contributions': cn, 'repoNum': rn}}
            data.append(personInfo)

            y = json.dumps(data)
            pprint(y)
            
         

