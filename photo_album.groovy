import java.net.*
import groovy.json.*
import java.util.regex.*

def input = null

while(!input?.equalsIgnoreCase("Q")) {
    String consoleDialog = 'Please input photo-album or photo-id followed by a number (ex: photo-album 3) OR enter Q to quit: '
    input = System.console().readLine consoleDialog
    if (verifyInput(input)) {
        printPhotos(input)
    } else {
        (!input?.equalsIgnoreCase("Q")) ? println("Input " + input + " invalid. Please try again.") :
                println("Goodbye")
    }
}

protected void printPhotos(input){
    JsonSlurper jsonSlurper = new JsonSlurper()
    jsonSlurper.parse(buildURLFromInput(input)).each {
        println("[" + it.id + "] " + it.title)
    }
}

protected Boolean verifyInput(String input){
    return input =~ /(photo-album |photo-id )\d+/
}

protected URL buildURLFromInput(String input){
    String addr = "https://jsonplaceholder.typicode.com/photos?" +
            (input.contains("album") ? "albumId=" : "id=")
    def id = input.findAll { it =~ /\d/ }.join()
    new URL(addr + id)
}
