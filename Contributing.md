# DriveWire 4 #

## Contributing Guide ##

While originally created by a single developer, this is now a community
driven project and all contributions are welcome.

## Reporting Issues ##

Any bugs should be reported through the issues tab in the github repository:
https://github.com/jimbro1000/DriveWire4Rebuild/issues

Please read through the open issues before reporting to help cut down on
duplicates. If you find the same problem already reported use the comments
option rather than posting a new issue report.

## Writing Code ##

Ideally your new code should be thoroughly unit tested (please use TDD)
to both prove your code works and that you haven't broken something else.
The current state of the code base has only a few unit tests present
where the original code has been reworked. This is a long way from ideal
but this isn't an excuse to skimp. This situation should improve over
time, and it needs your help to make it happen.

## Submitting Code ##

Make sure your code passes all the checks in the maven build process by
running `mvn clean install site` then checking the site report.

PMD, CPD, Checkstyle and Spotbugs reports should _all_ be clear. If you
need help please just ask. It is however your responsibility to make sure
everything is in a good state. Any transgressions on checkstyle will
result in your build failing while PMD, CPD and Spotbugs will only
generate a report and allow the build to complete.

Once everything is healthy the code will be reviewed via a pull-request,
and if accepted, it will be merged.

If there is a back-log of pull-requests, or we find merge conflicts, we
may ask you to rebase your changes against the main branch. It is your
responsibility to make sure the merge works as intended.
