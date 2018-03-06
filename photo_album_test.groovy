import photo_album
import java.net.*

final def classUnderTest = new photo_album()

/**
 * Test verifyInput
 */
assert classUnderTest.verifyInput("photo-album 3")
assert classUnderTest.verifyInput("photo-id 3")
assert !classUnderTest.verifyInput("phobo-id 3")
assert !classUnderTest.verifyInput("dakjDBSJA")
assert !classUnderTest.verifyInput("photo3")

/**
 * Test buildURLFromInput
 */
assert classUnderTest.buildURLFromInput("photo-album 3").sameFile(new URL("https://jsonplaceholder.typicode.com/photos?albumId=3"))
assert classUnderTest.buildURLFromInput("photo-id 3").sameFile(new URL("https://jsonplaceholder.typicode.com/photos?id=3"))

/**
 * TODO: Add unit tests for main once I figure out how to do stubbing in Groovy
 */
