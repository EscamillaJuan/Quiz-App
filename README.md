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
git checkout -b ToDo-#XXX

When you finish your ToDo, do the next steps
# 2. Update main branch
git checkout main
git pull origin main

# 3. Update your branch
git checkout ToDo-#XXX
git merge main

# 4. Merge your branch in the main branch
git checkout main
git merge --stagin ToDo-#XXX
git commit -m "message"
```
