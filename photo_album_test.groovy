import junit.framework.Test
import junit.framework.TestCase
import junit.textui.TestRunner
import photo_album


public class photo_album_tests extends TestCase {

    final def photoAlbum = new photo_album()

    public void testShouldReturnFalseOnNull(){
        assert !photoAlbum.verifyInput(null)
    }

    public void testShouldReturnFalseOnEmptyString(){
        assert !photoAlbum.verifyInput("")
    }

    public void testShouldReturnTrueOnProperPhotoAlbumInput(){
        assert photoAlbum.verifyInput("photo-album 3")
    }

    public void testShouldReturnTrueOnProperPhotoIdInput(){
        assert photoAlbum.verifyInput("photo-id 3")
    }

    public void testShouldReturnFalseOnInvalidUrl(){
        String input = ""
        String expectedUrl = "https://jsonplaceholder.typicode.com/photos?albumId=3"
        assert !photoAlbum.buildURLFromInput(input).sameFile(new URL(expectedUrl))
    }

    public void testShouldReturnTrueWhenBuildingUrlFromCommandStyle1(){
        String input = "photo-album 3"
        String expectedUrl = "https://jsonplaceholder.typicode.com/photos?albumId=3"
        assert photoAlbum.buildURLFromInput(input).sameFile(new URL(expectedUrl))
    }

    public void testShouldReturnTrueWhenBuildingUrlFromCommandStyle2(){
        String input = "photo-id 3"
        String expectedUrl = "https://jsonplaceholder.typicode.com/photos?id=3"
        assert photoAlbum.buildURLFromInput(input).sameFile(new URL(expectedUrl))
    }
}

public class RunTests{
    static Test suite() {
        def allTests = new GroovyTestSuite()
        allTests.addTestSuite(photo_album_tests.class)
        return allTests
    }
}

TestRunner.run(RunTests.suite())
