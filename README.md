# n64RomConverter
A Converter to Convert n64,z64 and v64 files. 
An already built jar-File can be found in the "release" Folder.

## USAGE

```txt
java -jar n64RomConverter.jar -i [INPUT] -o [OUTPUT] 
[OUTPUT] must have one of these Extensions: n64, v64, z64
```

## Example

We can use [ShufflePuck64](http://micro-64.com/features/shufflepuck64.shtml) as an example ROM to convert:

```bash
pushd ~/Downloads
curl -o ./shufflepuck64.zip http://micro-64.com/features/misc/shufflepuck64.zip
unzip ./shufflepuck64.zip
```

And we can convert it by downloading and running n64RomConverter:

```bash
git clone https://github.com/masl123/n64RomConverter

java -jar ./n64RomConverter/release/n64RomConverter.jar \
    -i ./shufflepuck64/Shuffle\ Puck\ 64\ \(PD\).z64 \
    -o ./shufflepuck64/Shuffle\ Puck\ 64\ \(PD\).n64
```

## Build & Test

```bash
git clone https://github.com/masl123/n64RomConverter

pushd ./n64RomConverter
javac com/github/masl123/n64RomConverter/Program.java
jar cMf n64RomConverter.jar META-INF/MANIFEST.MF com/github/masl123/n64RomConverter/Program.class

# Test the built jar file (./n64RomConverter.jar)
java -jar n64RomConverter.jar \
    -i ./shufflepuck64/Shuffle\ Puck\ 64\ \(PD\).z64 \
    -o ./shufflepuck64/Shuffle\ Puck\ 64\ \(PD\).n64
	
```
