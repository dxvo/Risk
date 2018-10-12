# UH COSC 4353 - Risk game Project [![Build Status](https://travis-ci.org/dxvo/Risk.svg?branch=master)](https://travis-ci.org/dxvo/Risk) [![codecov](https://codecov.io/gh/dxvo/Risk/branch/master/graph/badge.svg)](https://codecov.io/gh/dxvo/Risk)


Implementing a Risk game. Description of the game is available at [Risk](http://www.ultraboardgames.com/risk/index.php). The program should support  N={2,3,4,5,6} players. Program will prompt users to enter new actions

**Language**: Java 

**Game's** [Rules](http://www.ultraboardgames.com/risk/game-rules.php) and [How To Play](https://www.youtube.com/watch?v=-rqxpjOz-EA)

**To compile program:** ./build.sh 
# Updates:

**v.0.4**
- Notify players if their territories are under attack.
- Players can purchase in-game credit. Players can use the credit to buy cards, buy undo actions, or transfer the credits to another player.
- Post the number of territories conquered by each player on Twitter after each turn and at the end of the game [Reference1](https://developer.twitter.com/en/docs/developer-utilities/twitter-libraries.html) or [Reference2](http://twitter4j.org/en/index.html)
- Modify pom.xml to generate JavaDocs and class diagrams [Reference](https://maven.apache.org/plugins/maven-javadoc-plugin/examples/alternate-doclet.html
)

**v.0.3**
- Users can undo their actions
- Your Program uses Amazon S3 to replay games 
- Test cases to cover at least 50% of the code
- Show test coverage on the repository [page](https://blog.frankel.ch/travis-ci-tutorial-for-java-projects/)

**v.0.2**
-  Use Travis CI and show the status of the build.
- test
