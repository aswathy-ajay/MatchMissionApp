#Example for leaders and followers with preferences for each
leadersList = [[0,1,2,3],[1,0,2,3],[1,0,3]]
followersList = [[0,1,2,3],[1,0,2,3],[2,0,1,3],[0,1,2,3]]

num_leaders = len(leadersList)
num_followers = len(followersList)

#creates a list of length same as followers with value -1
match = [-1] * num_followers

#creates a list of length same as menList with value 0. The use of this is to track the index of the followers that has been proposed to
next_proposal = [0] * num_leaders

#indices of leaders list
free_leaders = list(range(num_leaders))

def galeShapley():
    while free_leaders:
        leader = free_leaders.pop(0)
        leader_prefs = leadersList[leader]
        
        if next_proposal[leader] >= len(leader_prefs):
            #check if the man has already finished proposing to all the woman from his preference list
            continue
        
        follower_index = next_proposal[leader]  #index of the follower that needs to be proposed to is given by the next proposal list 
        follower = leader_prefs[follower_index] #getting that follower which has not been proposed to
        
        #as we completed one proposal change the index in next_proposal
        next_proposal[leader] += 1
        
        if match[follower] == -1:   #woman is not engaged
            match[follower] = leader
        else:   #woman is engaged 
            current_partner = match[follower]
            follower_prefs = followersList[follower]
            
            if follower_prefs.index(leader) < follower_prefs.index(current_partner):
                match[follower] = leader
                free_leaders.append(current_partner)
            else:
                free_leaders.append(leader)
    
    final_matches = ["Not Matched"] * num_followers
    for follower in range(num_followers):
        if match[follower] != -1:
            final_matches[follower] = match[follower]
    
    print("Final matches (woman index -> man index):", final_matches)
  
galeShapley()
