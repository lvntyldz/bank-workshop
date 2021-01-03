what is ibatis ?

a jdbc framework
developers write sql, ibatis executes it using jdbc.
no more try/catch/finally/try/catch.
an sql mapper
automatically maps object properties to prepared statement parameters.
automatically maps result sets to objects.
support for getting rid of n+1 queries.
a transaction manager
ibatis will provide transaction management for database operations if no other transaction manager is available.
ibatis will use external transaction management (spring, ejb cmt, etc.) if available.
great integration with spring, but can also be used without spring (the spring folks were early supporters of ibatis).
what isn’t ibatis ?

an orm
does not generate sql
does not have a proprietary query language
does not know about object identity
does not transparently persist objects
does not build an object cache


    
[index için tıklayın](../README.md)
