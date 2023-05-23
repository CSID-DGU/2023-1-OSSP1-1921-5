#!/usr/bin/env python
# coding: utf-8

# In[25]:


from z3 import *

v1 = Int('v1')

input0, input1, input2 = Ints('input0 input1 input2')
check1, check2, check3 = Bools('check1 check2 check3')

solver = Solver()

solver.add(input0 == 0)
solver.add(Implies(CSE1222 == True, CSE1000 == True))

solver.add(input1 == If(input0 < 5, input0 + v1, input0))
solver.add(input2 == If(input0 < 5, input1 * v1, input1))

result = Bool('result')

solver.add(check1 == (input2 > 5))

print(solver.check())
m = solver.model()
print("input2  = %s" % m[input2])
print("input1  = %s" % m[input1])
print("input0  = %s" % m[input0])
print("v1     = %s" % m[v1])
print("result = %s" % m[check1])
print(solver)


# In[2]:


i=0
while True:
    i = i+1
    solver.push()
    solver.add(check1 == CSE2013)
    print(solver.check())
    if(solver.check()==unsat):
        print(solver)
        solver.pop()
        break;
    m = solver.model()
    print ("result = %s" % m[check1])
    solver.pop()


# In[37]:


solver.reset()
PRI2023, DES2023, CSE2013, CSE2019, ASW1234= Bools('PRI2023 DES2023 CSE2013 CSE2019 ASW1234')
solver.add(check1 == And(CSE2013, CSE2019))
print(solver)
print(solver.check())


# In[38]:


solver.add(CSE2019 == True)
print(solver)


# In[44]:


PRI2023, DES2023, CSE2013, CSE2019, ASW1234= Bools('PRI2023 DES2023 CSE2013 CSE2019 ASW1234')
solver.add(Implies(ASW1234 == True, CSE2013  == True))
solver.add(ASW1234 == True)


# In[45]:


print(solver.check())
m = solver.model()


# In[46]:


print(m)


# In[47]:


print(solver)


# In[24]:


# 인정과목이 얼벼다
while solver.check() == sat:
    model = solver.model()

    # False인 Bool 값 출력
    for d in model.decls():
        if model[d] == False:
            print(d, model[d])


# In[ ]:


check1 =Bool('check1')


