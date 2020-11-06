# n64RomConverter
A Converter to Convert n64,z64 and v64 files

## USAGE

```txt
N64CONVERTER -i [INPUT] -o [OUTPUT] 
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

java -jar ./n64RomConverter/release/N64Converter.jar \
    -i ./shufflepuck64/Shuffle\ Puck\ 64\ \(PD\).z64 \
    -o ./shufflepuck64/Shuffle\ Puck\ 64\ \(PD\).n64
```

## Build & Test

```bash
git clone https://github.com/masl123/n64RomConverter

pushd ./n64RomConverter
javac com/github/masl123/n64RomConverter/Program.java

# Test the built class file
java com/github/masl123/n64RomConverter/Program \
    -i ./shufflepuck64/Shuffle\ Puck\ 64\ \(PD\).z64 \
    -o ./shufflepuck64/Shuffle\ Puck\ 64\ \(PD\).n64
```
