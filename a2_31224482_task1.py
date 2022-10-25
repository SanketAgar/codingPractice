"""
FIT 9136 Assignment 1
Made by : Sanket Agarwal
Student ID : 31224482
Updated on : 06/06/2020

Task 1 : Reading names from the input file and defining person objects and their friends.
The Task 1 program defines a Person class with the outlined funtions and attributes as specified.
The program then uses a load_people function to read the file and loop through every single line in the file
and create Person objects for every single Person and then loop through the file again to define the persons friends.
"""


class Person:
    """
    This class is used to create person objects and store their name and a list of their friends.
    """

    instances = {}  # Dictionary used to save all the person objects.

    def __init__(self, first_name, last_name):
        """
        Initialises and assigns variables used by the Person class.
        :param first_name: String
            First name of the person
        :param last_name: String
            Last name of the person
        """
        self.first_name = first_name  # Assigning the first name
        self.last_name = last_name  # Assigning the last name
        self.friend_list = []  # Initialising the list
        self.__class__.instances[self.get_index()] = self  # Creating a copy of self in the instances dictionary

    def add_friend(self, friend_person):
        """
        Adds a Person object to the list of the friends of the Person
        :param friend_person: Person
            A person who is the friend of the Person Object
        """
        self.friend_list.append(friend_person)

    def get_name(self):
        """
        Returns the name of the person in a string value
        :return: String
            The first name and last name of the person concatenated together.
        """
        person_name = self.first_name + " " + self.last_name
        return person_name

    def get_friends(self):
        """
        Returns a list of the Person objects friends.
        :return: list[Person]
            A list containing Person objects friends with the person
        """
        return self.friend_list

    def get_index(self):
        """
        Returns a string of the name of the person to be used as index in dictionary
        :return: String
            The first name and last name of the person concatenated together with an '_'.
        """
        index_name = self.first_name + "_" + self.last_name
        return index_name


def load_people():
    """The load_people function loops through each line in the a2_sample_set.txt file and creates Person objects for every
    line in the document, it then loops through all the Person objects again and adds their friends.
     
    :return: list[Person]
        A list of all the Person objects."""

    input_file = open("a2_sample_set.txt", "r")  # Accessing the file.
    for each_line in input_file:  # Looping through each line in the file.
        person_details = each_line.split(": ")  # Splitting the line by a ':'.
        current_person = person_details[0]  # Accessing the first part of the split name.
        full_name = current_person.split(" ")  # Splitting the name with a ' '.
        first_name = full_name[0]  # Saving the first part of the name as the first name.
        last_name = full_name[1]  # Saving the second part of the name as the last name.
        Person(first_name, last_name)  # Creating the Person object with a first name and last name
    input_file.close()  # Closing the file.

    input_file = open("a2_sample_set.txt", "r")  # Accessing the file.
    for each_line in input_file:  # Looping through each line in the file.
        person_details = each_line.split(": ")  # Splitting the line by a ':'.
        current_person = person_details[0]  # Accessing the first part of the split name.
        current_person = current_person.split(" ")  # Splitting the name with a ' '.
        current_person_index = current_person[0] + "_" + current_person[
            1]  # Concatenating the first and last name with a '_'.
        current_person_object = Person.instances[
            current_person_index]  # Going through the person dictionary to find the current person object.
        person_friends = person_details[1]  # Taking the list of names.
        person_friends = person_friends.rstrip()  # Stripping the list of names off extra end spaces.
        person_friends_details = person_friends.split(", ")  # Splitting all friends name separately
        for each_friend in person_friends_details:  # Looping through all the friends
            each_friend = each_friend.split(" ")  # Splitting the name of the friend into first and last name
            each_friend_index = each_friend[0] + "_" + each_friend[1]  # Creating the index of the friends name
            each_friend_object = Person.instances[each_friend_index]  # Finding the Person object of the friend
            current_person_object.add_friend(
                each_friend_object)  # Adding the friend object as a friend of the Person object.
    input_file.close()  # Closing the file

    person_objects_list = []  # Define a List for the person objects
    for each_person_index in Person.instances:  # Loop through all the person objects in the dictionary
        person_objects_list.append(Person.instances[each_person_index])  # Add all the person objects into new list
    return person_objects_list  # Return the list of all the person objects


if __name__ == '__main__':
    pass
