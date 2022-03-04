<p align=center>
  <a href="https://www.codefactor.io/repository/github/opensrcerer/supersonic">
    <img src="https://www.codefactor.io/repository/github/opensrcerer/supersonic/badge" width=140/>
  </a>
  <a href="https://github.com/OpenSrcerer/supersonic/network">
    <img src="https://img.shields.io/github/forks/OpenSrcerer/supersonic?style=flat-square" width=86/>
  </a>
  <a href="https://github.com/OpenSrcerer/supersonic/issues">
    <img src="https://img.shields.io/github/issues/OpenSrcerer/supersonic?style=flat-square" width=140/>
  </a>
  <a href="https://app.travis-ci.com/github/OpenSrcerer/supersonic">
    <img src="https://app.travis-ci.com/OpenSrcerer/supersonic.svg" width=140/>
  </a>
</p>

<br>

<h2>
    NOTE: This project is still under heavy development.
    If you wish to talk about the project, 
    <a href="https://discord.gg/EHuhw2Qhgv">visit the Discord Server.</a>
</h2>

<h1 align=center>About</h1>
Supersonic is a bot made for Discord. It aims to provide a limitless music playing experience by putting the power in the hands of the user. It works through the Subsonic API, so any media server such as Airsonic or Subsonic support it.

<br>

<h1 align=center>Motivation</h1>
After larger music bots like Groovy and Rhythm were taken down by Youtube in an attempt to limit the usage of their API, it was clear that Discord music bots had to move away from media giants in order to not risk being taken down. Supersonic is the answer to Youtube's decision - as it is up to you to provide the server that streams the media.

<br>

<h1 align=center>Features</h1>

* Use your own Subsonic server!
* Powerful audio managment using Slash Commands & Buttons
* Choose from a collection of public music servers to use in your Discord server
* If you don't like the content they have, you can always make and add your own!

<br>

<h1 align=center>Initial Custom Server Support</h1>
Currently, this project connects to a known server to prove it is online and functioning as expected. However, most would prefer they host their own music. Currently, Docker allows you to pass environment variables to connect to your current server. It does this by passing the values via the .env file prior to the image being built by Docker. if you would like custom server support outside of Docker, this can currently be achieved by editing the default values in `src/main/kotlin/personal/opensrcerer/client/cache/SubsonicCache.kt` (right of the ?:)

The variables that need to be passed are as follows, if you are happy with the default values for these, feel free to leave them be:
- SUB_URL
- SUB_PORT
- SUB_USER
- SUB_PASS
- SUB_VERSION

<h1 align=center>
  GNU © OpenSrcerer
</h1>
