menList = [[0,1,2,3],[1,0,2,3],[2,0,1,3],[0,1,2,3]]
womenList = [[0,1,2,3],[1,0,2,3],[2,0,1,3],[0,1,2,3]]
match = [-1] * len(womenList)  #creates a list of length same as womenList with value -1
next_proposal = [0] * len(menList)  #creates a list of length same as menList with value 0. The use of this is to track the index of the girls that has been proposed to
free_men = list(range(len(menList)))  #indices of menList

def galeShapley():
    while free_men:
        man = free_men.pop(0)
        man_prefs = menList[man]
        woman_index = next_proposal[man] #checking which preference to check. starts with 0
        woman = man_prefs[woman_index]
        
        #as we completed one proposal change the index in next_proposal
        next_proposal[man] += 1  
        
        if match[woman] == -1: #woman is not engaged
            match[woman] = man
        else: #woman is engaged 
            current_partner = match[woman]
            woman_prefs = womenList[woman]
            
            if woman_prefs.index(man) < woman_prefs.index(current_partner):
                match[woman] = man
                free_men.append(current_partner)
            else:
                free_men.append(man)
    
    print("Final matches: ", match)

galeShapley()
