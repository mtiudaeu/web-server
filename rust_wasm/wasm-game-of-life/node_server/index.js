import * as wasm from "wasm-game-of-life";

var input = `
a
b
c
d
e
f
h
i
j
k
l
m
n
o
p
q
r
s
t
u
v
w
x
y
z
1
2
3
4
5
6
7
8
9
0
Up,null,{w down}
Up up,null,{w up}
Down,null,{s down}
Down up,null,{s up}
Left,null,{a down}
Left up,null,{a up}
Right,null,{d down}
Right up,null,{d up}
+f,f,{Shift down}f{Shift up}
+t,t,{Shift down}t{Shift up}
+g,g,{Shift down}g{Shift up}
+y,y,{Shift down}y{Shift up}
+F1,F1,{Shift down}{F1}{Shift up}
+F2,F2,{Shift down}{F2}{Shift up}
+F3,F3,{Shift down}{F3}{Shift up}
+F4,F4,{Shift down}{F4}{Shift up}
+F5,F5,{Shift down}{F5}{Shift up}
F1,F1,{F1}
F2,F2,{F2}
F3,F3,{F3}
F4,F4,{F4}
F5,F5,{F5}
`;


var template = `
~#input_key_code#::
KeyWait  #key_wait_key_code#
if(State = 1)
{
        IfWinActive ahk_id %wowid1%
        {
                ControlSend,,  #output_key_code#, ahk_id %wowid1%
        }
        IfWinActive ahk_id %wowid2%
        {
                ControlSend,,  #output_key_code#, ahk_id %wowid2%
        }
}
if(State = 2)
{
        IfWinActive ahk_id %wowid1%
        {
                ControlSend,,  #output_key_code#, ahk_id %wowid2%
        }
        IfWinActive ahk_id %wowid2%
        {
                ControlSend,,  #output_key_code#, ahk_id %wowid1%
        }
}
if(State = 3)
{
        ControlSend,,  #output_key_code#, ahk_id %wowid1%
        ControlSend,,  #output_key_code#, ahk_id %wowid2%
}
Return
`;


wasm.greet(input, template);


//mdtmp
// 1- get id for (input) text box one
// 2- get id for (output) text box one
// 2- get id from a button
// 4- prefill input with autokey input config
// 5- add listener to a button
// 6- listener -> run a rust algorithm
//    -> pass string instead of reading file.

// Maybe a lot of compilation error.... don't know






