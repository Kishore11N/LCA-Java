from github import Github
from pprint import pprint

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

valid = validate()
isValid = valid[0]
access_token = valid[1]
user_github = valid[2]
repoName = valid[3]

if isValid == True:
    g = Github(access_token)
    user = g.get_user(user_github)
    for repo in user.get_repos():
        print(repo.name)
    
