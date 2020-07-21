#!/bin/zsh

bundle check
bundleCheckStatus=$?

if [ 0 -ne $bundleCheckStatus ]; then
    echo "Updating Ruby gems.."
    source updateGems.sh
fi

bundle exec jekyll clean
bundle exec jekyll serve --trace --incremental --safe
