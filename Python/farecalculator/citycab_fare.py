# CityCab Fare Calculator

# rate per km for each vehicle
fare_rates = {
    "Economy": 10,
    "Premium": 18,
    "SUV": 25
}

def calculate_fare(distance, vehicle, hour):
    # check if vehicle exists
    if vehicle not in fare_rates:
        return "invalid"

    rate = fare_rates[vehicle]
    total = distance * rate

    # peak hours surge 
    if hour >= 17 and hour <= 20:
        total *= 1.5

    return total


# taking inputs from user
try:
    dist = float(input("Enter distance in km: "))
    veh = input("Enter vehicle type (Economy/Premium/SUV): ").title()
    time = int(input("Enter hour (0-23): "))

    result = calculate_fare(dist, veh, time)

    print("\n------ Ride Estimate ------")

    if result == "invalid":
        print("Service not available for selected vehicle.")
    else:
        print("Distance:", dist, "km")
        print("Vehicle:", veh)
        print("Time:", time)
        print("Estimated Fare: ₹", round(result, 2))

    print("---------------------------")

except:
    print("Invalid input. Please try again.")