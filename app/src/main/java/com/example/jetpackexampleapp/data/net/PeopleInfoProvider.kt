package com.example.jetpackexampleapp.data.net

import com.example.jetpackexampleapp.data.model.People


class PeopleInfoProvider {

    companion object {
        var peopleList = initPeopleList()

        /**
         * Initialises peopleList with dummy data
         */
        private fun initPeopleList(): MutableList<People> {
            var peoples = mutableListOf<People>()
            peoples.add(People(
                "Ray Wenderlich",
                "RW Campus",
                "123456",
                "RayWanderRW",
                1))
            peoples.add(People(
                "Ray Wenderlich",
                "RW Campus",
                "123456",
                "RayWanderRW1",
                2))
            peoples.add(People(
                "Ray Wenderlich",
                "RW Campus",
                "123456",
                "RaywanderRW2",
                3))
            peoples.add(People(
                "Ray Wenderlich",
                "RW Campus",
                "123456",
                "RayWanderRW3",
                4))
            peoples.add(People(
                "Ray Wenderlich",
                "RW Campus",
                "123456",
                "RayWanderRW4",
                5))
            return peoples
        }
    }
}