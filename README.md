# Trie-Based Stock

This Java application manages stock using a trie data structure.

## Overview

The Trie-Based Stock Management Application allows you to perform various operations on a stock of products using a trie data structure. The application reads operations from an input file, processes them using the trie-based stock implementation, and writes the results to an output file.

## Usage

1. Compile the Java source files using your preferred Java compiler.
2. Run the compiled program with the following command-line arguments:

    ```java Main TREE <InputFile> <OutputFile>```


- `<InputFile>`: The name of the input file containing operations to perform on the stock.
- `<OutputFile>`: The name of the output file to store the results of the operations.

3. The application will process the operations, perform them using the trie-based stock implementation, and write the results to the output file.

## Command-Line Arguments

- `<InputFile>`: Provide the name of the input file with the operations to be performed.
- `<OutputFile>`: Specify the name of the output file to store the results.

## Input Format

Each line in the input file represents an operation. The format of each line is as follows:

`<Operation> <Product> <Quantity>`

- `<Operation>`: The operation to perform (`compra`, `venta`, `unidades`, `listado`).
- `<Product>`: The product identifier (string).
- `<Parameters>`: Additional parameters based on the operation.

## Output Format

The application writes the results of each operation to the output file. The output format depends on the operation type:

- `compra` or `venta`: Describes the result of the operation on the stock.
- `unidades`: Reports the number of units of a product in stock.
- `listado`: Lists products in stock with matching prefixes and their quantities.

## Example

Suppose you have an input file named `input.txt`. Run the following command:

```java Main TREE input.txt output.txt```


The application will process the operations in `input.txt` using the trie-based stock implementation and store the results in `output.txt`.

## Requirements

- Java Compiler (JDK) to compile and run the Java program.

## License

This project is licensed under the [MIT License](LICENSE).
