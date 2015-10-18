Sometimes the emulator crashes and warning saying that the project has errors and cannot start appears.


# Details #

<p><b>Problem: </b> One of the XML files are opened while developer is trying to run the application.<br>
<br>
<p><b>Reason: </b> Access rights for files are violated<br>
<br>
<p><b>Solution: </b>
<br>1- Close all opened XML files in the project.<br>
<br>2- Go to their location from the tree showing different files in the project and delete any files with same name + out.<br>
<br><i><b>Eg:</b> XML file is called strings.xml . The output file causing the problem will be called strings.out.xml</i>
<br>3- Restart eclipse<br>
<br>4- Check there is no invisible running version of emulator by opening the task manager and closing any process named emulator.exe<br>
<br>
<p>Usually steps from 1 -> 3 are sufficient; however, sometimes step 4 will be needed as well