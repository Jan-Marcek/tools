#!/bin/bash
echo ""
echo "Start deploying..."
echo ""

TOMCAT="e:/tomcat/apache-tomcat-7.0.32/webapps/"
DIRECTORY=$TOMCAT"unifiedviews"
FILE=$TOMCAT"unifiedviews.war"

UNIFIED_VIEWS="e:/eea/comsode/UnifiedViews/"
UNIFIED_VIEWS_FRONTEND_TARGET=$UNIFIED_VIEWS"Core/frontend/target/unifiedviews.war"

if [ -d "$DIRECTORY" ]; then
  # Control will enter here if $DIRECTORY exists.
  rm -r $DIRECTORY
  echo "$DIRECTORY is deleted"
fi

if [ -f "$FILE" ]; then
  # Control will enter here if $DIRECTORY exists.
  rm -r $FILE
  echo "$FILE is deleted"

fi



cp $UNIFIED_VIEWS_FRONTEND_TARGET $TOMCAT
echo "$UNIFIED_VIEWS_FRONTEND_TARGET is deployed to $TOMCAT"
echo ""
echo "Deployment has been successfully"