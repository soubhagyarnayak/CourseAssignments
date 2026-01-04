# Edx UC San DiegoX CSE167x Computer Graphics

[Edx UC San DiegoX CSE167x Computer Graphics](https://learning.edx.org/course/course-v1:UCSanDiegoX+CSE167x+2T2018/)

[Link to download assignments](http://cseweb.ucsd.edu/~viscomp/classes/cse167/wi17/assignments.html)

## Fixing the old project in Mac OS Tahoe

### 1. The make file is executing the commands for Linux
The following check needs to be updated
```make
ifeq ($(shell sw_vers 2>/dev/null | grep macOS | awk '{ print $$2}'),macOS)
```

### 2. Linking to library GL fails
Remove "-lGL -lGLU" as on Mac we link to OpenGL and GLUT from framework.

### 3. ld: symbol(s) not found for architecture arm64
The './lib/mac/libfreeimage.a' library is for i386,x86_64 and not arm64

Install arm64 libfree library using brew 
```
arch -arm64 brew install freeimage
```

Copy the library to ./lib/mac/ directory.
```
cp /opt/homebrew/Cellar/freeimage/3.18.0/lib/libfreeimage.a .
/lib/mac/
```

## Running the program
```bash 
cd ./CSE167x-Computer-Graphics/hw0-linux_osx/

make 

./mytest3
```

1. Press i to move the kettle
2. Press o to take screenshot
3. Update the code in mytest.cpp line 153 as per instruction. Then again take screenshot using the previous steps.
4. Compare the screenshots using imagemagick.