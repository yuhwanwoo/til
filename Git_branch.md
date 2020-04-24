# Git branch

## 1. branch 관련 명령어

> Git 브랜치를 위해 root-commit을 발생시키고 진행하세요.

1. 브랜치 생성

   ```
   (master) $ git branch {브랜치명}
   ```

2. 브랜치 이동

   ```
   (master) $ git checkout {브랜치명}
   ```

3. 브랜치 생성 및 이동

   ```
   (master) $ git checkout -b {브랜치명}
   ```

4. 브랜치 삭제

   ```
   (master) $ git branch -d {브랜치명}
   ```

5. 브랜치 목록

   ```
   (master) $ git branch
   ```

6. 브랜치 병합

   ```
   (master) $ git merge {브랜치명}
   ```

   - master 브랜치에서 {브랜치명}을 병합

## 2. branch 병합 시나리오

> branch 관련된 명령어는 간단하다.
>
> 다양한 시나리오 속에서 어떤 상황인지 파악하고 자유롭게 활용할 수 있어야 한다.

### 상황 1. fast-foward

> fast-foward는 feature 브랜치 생성된 이후 master 브랜치에 변경 사항이 없는 상황

1. feature/test branch 생성 및 이동

   ```bash
   (master) $ git checkout -b feature/test
   Switched to a new branch 'feature/test'
   (feature/test) $
   ```

   

2. 작업 완료 후 commit

   ```bash
   $ touch test.txt
   $ git add .
   $ git commit -m 'complete test'
   ```

   

3. master 이동

   ```bash
   (feature/test) $ git checkout master
   Switched to a new branch 'master'
   (master) $
   ```

   

4. master에 병합

   ```bash
   (master) $ git merge feature/test
   Updating 5e99c38..927d410
   Fast-forward
    test.txt | 0
    1 file changed, 0 insertions(+), 0 deletions(-)
    create mode 100644 test.txt
   
   ```

   

5. 결과 -> fast-foward (단순히 HEAD를 이동)

   ```bash
   $ git log --oneline
   927d410 (HEAD -> master, feature/test) complete test
   5e99c38 Complete menu
   fbec7a3 init
   ```

   

6. branch 삭제

   ```bash
   $ git branch -d feature/test
   Deleted branch feature/test (was 927d410).
   ```

   

------

### 상황 2. merge commit

> 서로 다른 이력(commit)을 병합(merge)하는 과정에서 **다른 파일이 수정**되어 있는 상황
>
> git이 auto merging을 진행하고, **commit이 발생된다.**

1. feature/signout branch 생성 및 이동

   ```bash
   $ git checkout -b feature/signout
   Switched to a new branch 'feature/signout'
   
   ```

   

2. 작업 완료 후 commit

   ```bash
   $ touch signout.txt
   $ git add .
   $ git commit -m 'Complete signout'
   [feature/signout 51132e2] Complete signout
    1 file changed, 0 insertions(+), 0 deletions(-)
    create mode 100644 signout.txt
   
   ```

   

3. master 이동

   ```bash
   $ git checkout master
   Switched to branch 'master'
   ```

   

4. *master에 추가 commit 이 발생시키기!!*

   - **다른 파일을 수정 혹은 생성하세요!**

   ```bash
   $ touch hotfix.txt
   $ git add .
   $ git commit -m 'Hotfix'
   [master 936fb42] Hotfix
    1 file changed, 0 insertions(+), 0 deletions(-)
    create mode 100644 hotfix.txt
   
   $ git log --oneline
   936fb42 (HEAD -> master) Hotfix
   927d410 complete test
   5e99c38 Complete menu
   fbec7a3 init
   
   $ git checkout feature/signout
   Switched to branch 'feature/signout'
   
   $ git log --oneline
   51132e2 (HEAD -> feature/signout) Complete signout
   927d410 complete test
   5e99c38 Complete menu
   fbec7a3 init
   
   ```

   

5. master에 병합

   ```bash
   $ git checkout master
   Switched to branch 'master'
   
   $ git merge feature/signout
   Merge made by the 'recursive' strategy.
    signout.txt | 0
    1 file changed, 0 insertions(+), 0 deletions(-)
    create mode 100644 signout.txt
   
   ```

   

6. 결과 -> 자동으로 *merge commit 발생*

   - vim 편집기 화면이 나타납니다.
   - 자동으로 작성된 커밋 메시지를 확인하고, `esc`를 누른 후 `:wq`를 입력하여 저장 및 종료를 합니다.
     - `w` : write
     - `q` : quit
   - 커밋이 확인 해봅시다.

   ```bash
   # vim 화면 나오는데 esc누르고 :wq 입력
   
   #나와서 로그를 확인해 본다.
   $ git log --oneline
   2df880d (HEAD -> master) Merge branch 'feature/signout'
   936fb42 Hotfix
   51132e2 (feature/signout) Complete signout
   927d410 complete test
   5e99c38 Complete menu
   fbec7a3 init
   
   ```

   

7. 그래프 확인하기

   ```bash
   $ git log --oneline --graph
   *   2df880d (HEAD -> master) Merge branch 'feature/signout'
   |\
   | * 51132e2 (feature/signout) Complete signout
   * | 936fb42 Hotfix
   |/
   * 927d410 complete test
   * 5e99c38 Complete menu
   * fbec7a3 init
   
   ```

8. branch 삭제

   ```bash
   $ git branch -d feature/signout
   Deleted branch feature/signout (was 51132e2).
   
   ```

   

------

### 상황 3. merge commit 충돌

> 서로 다른 이력(commit)을 병합(merge)하는 과정에서 **같은 파일의 동일한 부분이 수정**되어 있는 상황
>
> git이 auto merging을 하지 못하고, 충돌 메시지가 뜬다.
>
> 해당 파일의 위치에 표준형식에 따라 표시 해준다.
>
> 원하는 형태의 코드로 직접 수정을 하고 직접 commit을 발생 시켜야 한다.

1. feature/board branch 생성 및 이동

   ```bash
   $ git checkout -b feature/board
   ```

   

2. 작업 완료 후 commit

   ```bash
   $ touch board.txt
   # README.md 생성 파일 수정
   $ git add .
   $ git commit -m 'Complete'
   ```

   

3. master 이동

   ```bash
   $ git checkout master
   
   ```

   

4. *master에 추가 commit 이 발생시키기!!*

   - **동일 파일을 수정 혹은 생성하세요!**

   ```bash
   # README.md 파일 수정
   $ git add .
   $ git commit -m 'read update'
   [master 08f4dd9] read update
    1 file changed, 4 insertions(+)
   ```

   

5. master에 병합

   ```bash
   $ git merge feature/board
   Auto-merging README.md
   CONFLICT (content): Merge conflict in README.md
   Automatic merge failed; fix conflicts and then commit the result.
   
   ```

   

6. 결과 -> *merge conflict발생*

   > git status 명령어로 충돌 파일을 확인할 수 있음.

   ```bash
   $ git status
   On branch master
   You have unmerged paths.
     (fix conflicts and run "git commit")
     (use "git merge --abort" to abort the merge)
   
   Changes to be committed:
           new file:   board.txt
   
   Unmerged paths:
     (use "git add <file>..." to mark resolution)
           both modified:   README.md
   
   ```

   

7. 충돌 확인 및 해결

   ```bash
   $ git merge feature/board
   error: Merging is not possible because you have unmerged files.
   hint: Fix them up in the work tree, and then use 'git add/rm <file>'
   hint: as appropriate to mark resolution and make a commit.
   fatal: Exiting because of an unresolved conflict.
   
   $ git add .
   ```

   

8. merge commit 진행

   ```bash
   $ git commit
   ```

   - vim 편집기 화면이 나타납니다.
   - 자동으로 작성된 커밋 메시지를 확인하고, `esc`를 누른 후 `:wq`를 입력하여 저장 및 종료를 합니다.
     - `w` : write
     - `q` : quit
   - 커밋이 확인 해봅시다.

9. 그래프 확인하기

   ```bash
   $ git log --oneline --graph
   ```

   

10. branch 삭제

    ```bash
    $ git branch -d feature/branch
    Deleted branch feature/board (was 202e282).
    
    ```

    