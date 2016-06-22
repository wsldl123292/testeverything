parser grammar SQLParser;

options
   { tokenVocab = SQLLexer; }

stat
   : select_clause
   ;

select_clause
   : SELECT column_list_clause FROM table_references  ( where_clause )? ( limit_case )?
   ;

table_name
   : ID
   ;

table_type
   : ID
   ;

limit_case
   : LIMIT limitexpre
   ;


limitexpre
   : limitNum COMMA limitNum
   ;

limitNum
   : INT
   ;

column_name
   : ID
   ;

column_list_clause
   : ALL_FIELDS | column_name ( COMMA column_name )*
   ;

where_clause
   : WHERE expression
   ;

expression
   : LPAREN expression RPAREN       #parenExp
   | expression AND expression      #andExp
   | expression OR expression       #orExp
   | simple_expression              #exp
   ;

element
   : ID | INT
   ;

right_element
   : element
   ;

left_element
   : element
   ;

target_element
   : element
   ;

relational_op
   : EQ | LTH | GTH | NOT_EQ | LET | GET | LIKE
   ;

expr_op
   : AND | OR | NOT
   ;

between_op
   : BETWEEN
   ;

is_or_is_not
   : IS | IS NOT
   ;



simple_expression
   : left_element relational_op right_element                                 #baseExp
   | target_element between_op LPAREN left_element COMMA left_element RPAREN  #betweenExp
   | target_element is_or_is_not NULL                                         #isExp
   ;

table_references
   : table_name DOT table_type ( ( COMMA table_name DOT table_type ))*
   ;