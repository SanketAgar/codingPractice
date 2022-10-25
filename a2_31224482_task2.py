"""
FIT 9136 Assignment 1
Made by : Sanket Agarwal
Student ID : 31224482
Updated on : 06/06/2020

Task 2 : Extending the Person class from task 1 to a patient class and running a simulation that outlines the spread of
the virus in a specified scenario.
.
The Task 2 program defines a Patient sub class with the outlined functions and attributes as specified, building up on the
parent class Person which was defined in task 1.
The program then uses a run_simulation function to pass all the patients through a specified scenario and checks for the
spread of virus in the given situation.
"""

from a2_31224482_task1 import *  # Importing all the functions and classes from task 1.
import random  # Importing the random library.


class Patient(Person):
    """
    A sub class of the Person class, this class builds on the person class with additional variables needed to run the
    simulation of the virus spread.
    """

    patient_list = []  # patient_list stores a list of all the patient objects.

    def __init__(self, first_name, last_name, health):
        """
        Outlines the variables used  of the Patient class and assigns them based on the input parameters,
        :param first_name: string
            Defines the first_name of the patient.
        :param last_name: string
            Defines the last_name of the patient.
        :param health: string
            Defines the current health of the patient.
        """

        # Assigning the first and last name of the patient object based on the values of the parent class.
        super().__init__(first_name, last_name)

        # Assigning the health of the patient based on the input parameter.
        self.health = health

    def get_health(self):
        """
        A function that return the current health of the patient object.
        :return: float
            The current health of the player, value between 1 - 100.
        """
        return self.health

    def set_health(self, new_health):
        """
        The function changes the value of the patients current health based on the input parameter.  The function then
        validates the range of the health value to be in the allowed range of 0 - 100.
        :param new_health: float
            A value that is assigned as the new health of the patient.
        """
        self.health = new_health

        # Validating the health is not lower than 0.
        if self.health < 0:
            self.health = 0

        # Validating the health is not greater than 100.
        elif self.health > 100:
            self.health = 100

    def is_contagious(self):
        """
        The function checks the current health of the patient object and defines whether the patient is contagious or not.
        :return: Boolean
            True or false defines if the person is contagious or not.
        """
        if self.health <= 49:
            return True
        else:
            return False

    def infect(self, viral_load):
        """
        The infect function is called when the patient comes in contact with a contagious patient. The infect function
        takes an input of the contagious patient's viral load as the parameter, then it checks the current health of the
        patient and defines how the viral load will affect them. It then updates the health of the patient by calling the
        set_health function.
        :param viral_load: float
            The viral load of the contagious patient that came into contact with the patient object.
        """

        # Storing the current health of the patient.
        old_health = self.get_health()

        # Checking if current health of the patient is less than 29, if it is, then the patient experiences reduced effects
        # from the viral load.
        if old_health <= 29:
            new_health = old_health - (0.1 * viral_load)
            self.set_health(new_health)

        # Checking if current health of the patient is greater than 29 and less than 50, if it is, then the patient experiences
        # moderate effect from the viral load.
        elif 29 < old_health < 50:
            new_health = old_health - (1.0 * viral_load)
            self.set_health(new_health)

        # Checking if current health of the patient is greater than 50, if it is, then the patient experiences increased
        # effect from the viral load.
        elif 50 <= old_health:
            new_health = old_health - (2.0 * viral_load)
            self.set_health(new_health)

    def sleep(self):
        """
        Simulates the patient sleeping and regaining 5 health points at the end of the day
        """
        old_health = self.get_health()
        new_health = old_health + 5
        self.set_health(new_health)


def run_simulation(days, meeting_probability, patient_zero_health):
    """
    The run simulation function follows the guidelines specified in the assignment tasks and goes through a plethora of
    conditions to simulate the spread of the virus.

    :param days: int
        The number of days the simulation will run for.
    :param meeting_probability: double between 0-1
        The chances of a person going to meet each of their friends.
    :param patient_zero_health: double between 0 - 100
        The health of the first person who gets infected.
    :return: list[int]
        Returns a list containing the a set of numbers which defines the number of contagious patients at the end of
        each day.
    """

    # Calling the load_patients function to load all the patient objects with initial health 75.
    patient_list = load_patients(75)

    # Setting the health of the first infected patient to the parameter value.
    Patient.patient_list[0].set_health(patient_zero_health)

    # Defining an iterator variable called everyday.
    everyday = 1

    # Defining a list variable to store the number of contagious patients at the end of the day.
    total_contagious = []

    # Using a loop to cycle through every day.
    while everyday <= days:
        everyday += 1  #  Incrementing the loop iterator by 1.
        for every_patient in patient_list:  # Looping through all the patients.
            friend_list = every_patient.get_friends()  # Getting the list of the patients friends.
            for every_friend in friend_list:  # Looping through the friends list.
                meeting_chance = random.random()  # generating a random number between 0-1 which defines the chance to meet their friend.
                if meeting_chance <= meeting_probability:  # Checking if the random meeting chance is a successful meet up or not.

                    if every_friend.is_contagious():  # Checking if the friend who the patient met is contagious our not.
                        friend_health = every_friend.get_health()  # Getting the health of the friend if they are contagious.
                        viral_load = 5 + (((friend_health - 25) ** 2) / 62)  # Generating the viral load of the friend.
                        every_patient.infect(viral_load)  # Infecting the patient with the viral load of the friend.

                    if every_patient.is_contagious():  # Checking if the patient is contagious.
                        patient_health = every_patient.get_health()  # Getting the health of the patient if they are contagious.
                        viral_load = 5 + (((patient_health - 25) ** 2) / 62)  # Generating the viral load of the patient.
                        every_friend.infect(viral_load)  # Infecting the friend with the viral load of the patient.

        total_contagious_counter = 0  # Variable to save the total number of contagious people at the end of the day.
        for every_patient in patient_list:  # looping through all the patients
            if every_patient.is_contagious():  # Checking if the person is contagious or not
                total_contagious_counter += 1  # Incrementing the variable by 1 if the person is contagious
        total_contagious.append(total_contagious_counter)  # Adding the final value to the list

        for every_patient in patient_list:  # Looping through all the patients.
            every_patient.sleep()  # Making the patients sleep.

    return total_contagious  # Returning a list that outlines the total number of infected people at the end of each day.


def load_patients(initial_health):
    """
    The load_patients function loops through each line in the a2_sample_set.txt file and creates patients objects for every
    person in the document, it then loops through all the patient objects again and adds their friends.

    :param initial_health: int
        The initial health of all the patient objects.
    :return: list[Patient]
        A list of all the patient objects.
    """
    input_file = open("a2_sample_set.txt", "r")  # Accessing the file.
    for each_line in input_file:  # Looping through each line in the file.
        patient_details = each_line.split(": ")  # Splitting the line by a ':'.
        current_patient = patient_details[0]  # Accessing the first part of the split name.
        full_name = current_patient.split(" ")  # Splitting the name with a ' '.
        first_name = full_name[0]  # Saving the first part of the name as the first name.
        last_name = full_name[1]  # Saving the second part of the name as the last name.
        Patient(first_name, last_name, initial_health)  # Creating the Patient object with a first name and last name along with the parameter initial health.
    input_file.close()  # Closing the file.

    input_file = open("a2_sample_set.txt", "r")  # Accessing the file.
    for each_line in input_file:  # Looping through each line in the file.
        patient_details = each_line.split(": ")  # Splitting the line by a ':'.
        current_patient = patient_details[0]  # Accessing the first part of the split name.
        current_patient = current_patient.split(" ")  # Splitting the name with a ' '.
        current_person_index = current_patient[0] + "_" + current_patient[1]  # Concatenating the first and last name with a '_'.
        current_person_object = Person.instances[current_person_index]  # Going through the person dictionary to find the current person object.
        person_friends = patient_details[1]  # Taking the list of names.
        person_friends = person_friends.rstrip()  # Stripping the list of names off extra end spaces.
        person_friends_details = person_friends.split(", ")  #Splitting all friends name separately
        for each_friend in person_friends_details:  # Looping through all the friends
            each_friend = each_friend.split(" ")  # Splitting the name of the friend into first and last name
            each_friend_index = each_friend[0] + "_" + each_friend[1]  # Creating the index of the friends name
            each_friend_object = Person.instances[each_friend_index]  # Finding the Person object of the friend
            current_person_object.add_friend(each_friend_object)  # Adding the friend object as a friend of the Person object.
    input_file.close()  # Closing the file

    for each_index in Person.instances:  # Looping through all the objects in the dictionary
        Patient.patient_list.append(Person.instances[each_index])  # Saving all the Person objects from the dictionary as a Patient list.

    return Patient.patient_list  # Returning the list with all the patient objects.


if __name__ == '__main__':
    pass
