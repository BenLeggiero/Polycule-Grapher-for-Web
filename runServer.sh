#!/bin/zsh

bundle check
bundleCheckStatus=$?

if [ 0 -ne $bundleCheckStatus ]; then
    echo "Updating Ruby gems.."
    source updateGems.sh
fi

rm -r _site/

# jekyll serve --trace --incremental
bundle exec jekyll clean
bundle exec jekyll serve --trace --incremental --safe
