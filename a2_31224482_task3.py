"""
FIT 9136 Assignment 1
Made by : Sanket Agarwal
Student ID : 31224482
Updated on : 06/06/2020

Task 3 : Adds more detail to the simulation presented in task 2 by plotting a graph of the results.
from task 1 to a patient class and running a simulation that outlines the spread of
the virus during a specified scenario.
The Task 3 program has only one method called visual_curve which takes in three parameters, these parameters define
the values that will be used in the simulation, such as, the number of days in the simulation, the percentage chance of
a person meeting their friend and the starting health condition of the first patient. The method then calls the run_simulation
function from task 2 using these values and uses matplotlib to plot the results onto a graph.

Scenario 1 : The graph has a steep upwards curve as the probablity of meeting people is high, it flattens out a little towards
the end because some people only have one friend and it's difficult to infect them.

Scenario 2 : The second scenario has slight chances of spread of the virus and thus the virus only spreads to a very
few people before dying out.

Scenario 3 : The third scenario has very little chances of the virus ever spreading and in most cases results in the
infected person getting cured without ever spreading the virus as outlines in the output graph.
"""
import matplotlib.pyplot as graph  # importing matplotlib's pyplot function to plot the graphs.
from a2_31224482_task2 import *  # import statement to make use of functions/classes from earlier task(s).


def visual_curve(days, meeting_probability, patient_zero_health):
    """
    The visual curve function calls the run_simulation code from task 2, stores that data and then plots a graph
    based on that data.

    PARAMETERS
    ----------

    :param days: int
        The number of days the simulation will run for.
    :param meeting_probability: double between 0-1
        The chances of a person going to meet each of their friends.
    :param patient_zero_health: double between 0 - 100
        The health of the first person who gets infected.
    """

    # Calling the run_simulation function and saving the returned list as contagious_list.
    contagious_list = run_simulation(days, meeting_probability, patient_zero_health)
    # Creating a list of all numbers from 1 to the entered day, adding a 1 to the days variable to make it inclusive
    days_list = list(range(1, days+1))
    # Naming the Y axis of the graph.
    graph.ylabel('Contagious Patient')
    # Naming the X axis of the graph.
    graph.xlabel('Days in Simulation')
    # Creating a new variable to get the value of the maximum contagious people and using it to define the axis size of
    # the graph.
    max_contagious = max(contagious_list)+10
    graph.axis([0, days, 0, max_contagious])
    # Plotting the graph with days_list as the X axis value and the contagious_list as the Y axis values.
    graph.plot(days_list, contagious_list)
    # Debug statement that prints the contagious_list.
    print(contagious_list)
    # Displaying the graph to the user.
    graph.show()


def main():
    """
    The main function takes the three inputs from the user for the parameters that are used to then call the
    visual_curve function"
    """

    # Taking the input for the number of days the simulation will run for. Accepted values : any integer.
    sim_days = int(input('Enter the number of days for the simulation : '))

    # Taking the input for the probability of a person meeting their friends. Accepted values : 0-1.
    meeting_chance = float(input('Enter the probability of a person meeting their friends : '))

    # Taking the input for the starting health of the first person who is infected by the virus. Accepted values : 0 - 100.
    first_victim_health = float(input('Enter the starting health of patient zero in the simulation : '))

    # Calling the visual_curve function using the input values.
    visual_curve(sim_days, meeting_chance, first_victim_health)

if __name__ == '__main__':
    main()

