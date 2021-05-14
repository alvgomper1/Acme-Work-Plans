README.txt
==========

This file documents how to upgrade a D&T project from version 21.3 to version 21.4.  In most cases, the upgrade must work automatically.  Follow the following instructions and everything should work well.  There's an additional video tutorial available at:

	https://eu-lti.bbcollab.com/recording/94f72bbb86464c58b347a8ce42b4d125

Upgrading your projects
-----------------------

Using the upgrade procedure is very simple.  You've got a zip file called "upgrade-21.3-21.4.zip".  Decompress it to a folder and make your command shell for that folder. Now, copy project "Starter-Project" into that folder.  Then execute the following command:

	.\upgrade-21.3-21.4.cmd Starter-Project

It returns the following text:

	This utility allows to upgrade a project from version 21.3 to version 21.4.
	It's safe to use it as long as you haven't changed the framework.
	Project "Starter-Project" will be backed up as "Starter-Project;20210511152001" if you proceed.

	Type 'y' to confirm or 'n' to abort the upgrade Y

This informs you that the utility is intended to upgrade your projects, which is safe as long as you didn't make any changes to the framework.  It also informs you that it's going to back your project folder up and asks for confirmation.  Press the 'y' key and let's go ahead.  It will display the following text:

	Backing up your project.

	316 File(s) copied

	Las opportunity to cancel. Type 'y' to proceed or 'n' to abort Y

This informs you that it's copied your project and gives you the last opportunity to cancel the process.  Press 'y' to go ahead.  The script will display the following text if there are not any errors:

	Upgrading your project.

	Patching file "Starter-Project\pom.xml"
	Patching file "Starter-Project\clevercloud\war.json"
	Patching file "Starter-Project\CHANGELOG.txt"
	Patching file Starter-Project\src\main\properties\banner.txt
	Copying a few files...
	.\patches\StringHelper.java
	1 File(s) copied
	.\patches\framework-testing\AbstractTest.java
	.\patches\framework-testing\WaitConditions.java
	2 File(s) copied
	.\patches\sample-testing\AcmeTest.java
	.\patches\sample-testing\SignUpTest.java
	2 File(s) copied

	Upgrading finished.  If there were any errors, please consult
	the README.txt file and perform a manual upgrade.

The output informs you of the files that are being patched or copied.  If no errors are displayed, then everything has worked well and you can keep working normally; otherwise, you should recover your project from the backup copy and upgrade it manually.  Please, consult the troubleshooting section for additional details.

To upgrade project "Acme-Jobs" execute the following command: 

	.\upgrade-21.3-21.4.cmd Acme-Jobs

The output should be very similar to the previous one and there shouldn't be any problems. 

It's time to upgrade your "Acme-Planner" project using the following command:

	.\upgrade-21.3-21.4.cmd Acme-Planner


Troubleshooting
---------------

If the automatic upgrade fails, then there's really little you can do to get it working.  Very likely, you've changed something in the framework and the upgrade can't work automatically.   In that case, you'll have to upgrade your project manually.  The changes to this version are described next, where "<project>" denotes the folder where the project to upgrade resides.

- Update file "<project>\pom.xml" by changing the "<version>" element.
- Update file "<project>\clevercloud\war.json" by changing the version of the war file.
- Update file "<project>\CHANGELOG.txt" by adding the following line: "21.4   The testing framework has been greatly simplified and enhanced."
- Update file "<project>\src\main\properties\banner.txt" by changing the version number.
- Copy file ".\patches\StringHelper.java" to folder "<project>\src\main\java\acme\framework\helpers"
- Copy the files in folder ".\patches\framework-testing" to folder "<project>\src\main\java\acme\framework\testing"
- Copy the files in folder ".\patches\sample-testing" to folder "<project>\src\test\java\acme\testing"
