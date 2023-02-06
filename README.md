# BlackJack Project

# Description
- This project simulates a blackjack game.
- It is for 1 - 3 human players and a computer dealer
- each player plays against the dealer
- Aces are 11
- each play can hit or stand
- each player is only playing against the dealer. It is not a bug that two players can be winners because they aren't playing against one another. I might not have the rules right but it IS intentional. This also means the game does not end automatically when someone gets 21.

# Technologies Used
Java, Eclipse IDE, git

# Lessons Learned
- be sure to cast or will be calling wrong method (cast or use super if that's what it is)
- in an app loop, make sure everything is instantiated newly or LISTS are cleared
- keep println()s local (when they are spread out there are messages all over and it's annoying and sloppy)
- Interface is a helpful way to check type of something. I made an IDealer interface that is implemented by the BJDealer and it's useful to check
 for this implementation when checking the player list for behavior (dealer or player) using instanceof. I found it useful even though I only have one class implementing it. Interfaces would be a really great tool for planning out what methods are needed. Also if I wanted more types of dealer I could use IDealer to make sure they implemented common methods and could all be treated as dealer whether they were a separate dealer or involved in a game as a player too.

# Bugs
- one major problem is that because ACES are always 11 right now if the dealer or player gets two, it will look like they busted right off the bat when in real life this is impossible. This is included in the THINGS TO DO LIST below.

# Things to Do (before the end of SD37 because it would be fun and a good thing to show)
- Aces 11 or 1
- betting
- split
- double down
- insurance (never take insurance!)
- multiple decks (shoe)
- player names
- better output/user interface (as much as can be done with println())
- lots of other things I didn't do...
- better exception handling (still using basic Exception e)

