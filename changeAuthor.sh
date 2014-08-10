#!/bin/sh
 
git -f filter-branch --env-filter '
 
an="$GIT_AUTHOR_NAME"
am="$GIT_AUTHOR_EMAIL"
cn="$GIT_COMMITTER_NAME"
cm="$GIT_COMMITTER_EMAIL"
 
if [ "$GIT_COMMITTER_EMAIL" = "scrumretrospective@gmail.com"]
then
    cn="Sanju Thomas"
    cm="sanju@sanju.org"
fi
if [ "$GIT_AUTHOR_EMAIL" = "scrumretrospective@gmail.com" ]
then
    an="Sanju Thomas"
    am="sanju@sanju.org"
fi
 
export GIT_AUTHOR_NAME="$an"
export GIT_AUTHOR_EMAIL="$am"
export GIT_COMMITTER_NAME="$cn"
export GIT_COMMITTER_EMAIL="$cm"
'
