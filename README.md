# web-scraping

This is a web-scraper which creates a RESTful API of this website:
https://s3.us-east-2.amazonaws.com/gsd-auth-callinfo/callnotes.html

## Installation

1. Install [Leiningen](https://leiningen.org/)

2. Clone this library.

3. To run tests using command prompt, run ```lein test``` in project root directory

## Usage

To start the server, change directory to this library and run this command:

```lein ring server```

The server should be accessible at http://localhost:3000. There are several routes that you can access:

/posts

/posts/### (Note: This route will only show results which contain the 3-digit area-code put into the URI. If no results are found, then the page will be empty.)

/area-codes

/phone-numbers

/report-amounts

/comment-contents

## Examples

As of 05/12/2017, accessing http://localhost:3000/posts/844 should give one result.

## License

Copyright © 2017 Paul Nguyen

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
