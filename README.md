# RTree
JSON-like Encoding/Decoding Language &amp; Data structure.

<p>
Java written implementation of encoding/decoding language for data structure. <br>
To use this package, you'd only need to import 3 classes:
<ul>
  <li>
    RNode
  </li>
  <li>
    RData
  </li>
  <li>
    RTree
  </li>
</ul>
After importing those, you'd easily be able to encode/decode your data structures.
To do so, you can make use of the functions:
<ul>
  <li>
    <h3>String basic_encode(String[][] info);</h3> 
    <p>This method takes a bi-dimensional array as parameter, then it proceeds to build a tree
    with a single node. The root of this tree holds the data you sent as info. Returns the encoded 
    representation of this tree.</p>
    <h4>Example:</h4>
    <p>
    {{"username","user1234"},{"password","pass12345"}} => {[username:&lt;user1234&gt;|password:&lt;pass12345&gt;]}
    </p>
  </li>
  <li>
  <h3>RData basic_decode(String source);</h3> 
    <p>
    This method takes an encoded RTree and returns the RData stored in the root.
    This method is intended to be used when you expect the tree to have only one node. This usually happens
    when you use the simple_encode method, for sharing simple data like login packages.
    You can access the fields from the decoded source by calling RData.getValue("name");
    </p>
    <h4>
    Example
    </h4>
    <p>
    RData result = RTree.basic_decode(message);<br>
    print(result.getValue("username"));<br>
    print(result.getValue("password"));
    </p>
  </li>
  <li>
  <h3>RNode decode(String source);</h3>
  <p>
  This method takes an encoded tree and returns the actual usable decoded structure.
  </p>
  </li>
</ul>
There's some other functions which you can find commented in the source code.<br>
<h2>Data Structures</h2>
<p>
This package makes use of 2 important data structures and this are the ones you can use
to encode data, decode data and access decoded fields. The Data Structures you'll find are:
<ul>
<li>
  <h3>RNode</h3>
  <p>
    This class is a simple implementation of a node for a n-Tree. It has a LinkedList<RNode> Children.
    And It has RData data.
  </p>
</li>
<li>
  <h3>RData</h3>
  <p>
    This class uses a HashMap to store the fields. The name of the field is used as key
    and the value of the field as value for the HashMap. <br>
    The values this HashMap can store are:
    <ul>
      <li>
        String  
      </li>
      <li>
        RData
      </li>
      <li>
        RNode
      </li>
    </ul>
    <br>
    You must know what data type you're expecting to get from the field.
    You can then access the value by calling:
    <ul>
      <li>
        getValue("name");<br>
        Returns the String held in the value for key:"name"; If no string found, prints an alert and returns null.
      </li>
      <li>
        getData("name");<br>
        Returns the RData held in the value for key:"name"; If no RData found, prints an alert and returns null.
      </li>
      <li>
        getTree("name");<br>
        Returns the RNode held in the value for key:"name"; If no RNode found, prints an alert and returns null.
      </li>
    <ul>
  </p>
</li>
</ul>
</p>
</p>
