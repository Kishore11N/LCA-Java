from github import Github
from pprint import pprint

username = input("Please enter username : ")
access_token = input("Please enter your access token : ")
repoName = input("Please enter the name of the repository : ")

g = Github(access_token)
user = g.get_user(username)
for repo in user.get_repos():
    print("The name of the repositories are : " + repo.name)


