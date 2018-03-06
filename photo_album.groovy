import java.net.*
import groovy.json.*
import java.util.regex.*

def input = null

while(!input?.equalsIgnoreCase("Q")) {
    //User inputs desired photo album or photo
    input = System.console().readLine 'Please input photo-album or photo-id followed by a number (ex: photo-album 3) OR enter Q to quit: '
    if (verifyInput(input)) {
        //Print id and title of each photo in the console
        JsonSlurper jsonSlurper = new JsonSlurper()
        jsonSlurper.parse(buildURLFromInput(input)).each {
            println("[" + it.id + "] " + it.title)
        }
    } else {
        //Loop back to the beginning or quit
        (!input?.equalsIgnoreCase("Q")) ? println("Input " + input + " invalid. Please try again.") :
                println("Goodbye")
    }
}


protected Boolean verifyInput(String input){
    //Input must be "photo-album # or photo-id #"
    return input =~ /(photo-album |photo-id )\d+/
}

protected URL buildURLFromInput(String input){
    String addr = "https://jsonplaceholder.typicode.com/photos?" +
            (input.contains("album") ? "albumId=" : "id=")
    def id = input.findAll { it =~ /\d/ }.join()
    new URL(addr + id)
}