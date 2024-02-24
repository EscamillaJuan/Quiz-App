# Quiz-App
Android application 

##   Git Flow

Each time you start a new ToDo, another developer can do the same and merge his branch before you, in that case your branch need to be updated before merge with main.  
You can see this problem in the next figure.
![image](https://github.com/EscamillaJuan/Quiz-App/assets/84486053/cb7e82b7-2d7a-488d-87ad-f1bb2a7ad294)  

To avoid this problem, follow the next steps when you start a new ToDo.

```
# 1. Create your branch
git pull origin main
git checkout -b ToDo-#XXX //name your branch as ToDo-#XXX where XXX is the ToDo number from dashboard

When you finish your ToDo, do the next steps after add and commit your changes
# 2. Update main branch
git checkout main
git pull origin main

# 3. Update your branch
git checkout ToDo-#XXX
git merge main

# 4. Merge your branch in the main branch
git checkout main
git merge --squash ToDo-#XXX
git commit -m "message"
```

# commit message
I recommend to use the next prefix when you do your commit:  
- feat: A new feature
- fix: A bug fix
- docs: Documentation only changes
- style: Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)
- refactor: A code change that neither fixes a bug nor adds a feature
- perf: A code change that improves performance
- test: Adding missing tests or correcting existing tests
- chore: Other changes that don't modify src or test files
- revert: Reverts a previous commit

Example: git commit -m "feat: add question model"  
