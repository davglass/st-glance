My Home at a Glance
===================

This is a little hack that I put together to play with SmartDevice types and doing different things with them.

It gives me a *Whole house view* of my various sensors and readings. Doesn't do much besides count things.

![img_7907](https://cloud.githubusercontent.com/assets/32551/7352870/c18763c4-ecd4-11e4-9829-dbae3cd898c1.PNG)


Install Device
--------------

   * Go to the ST IDE and head over to [`My Device Types`](https://graph.api.smartthings.com/ide/devices)
   * Click on `New SmartDevice`
   * Select `From Code`
   * Paste in the code from `glance.device.groovy`
   * Save and Publish this

Create the Device
-----------------

   * Go to the ST IDE and head over to [`My Devices`](https://graph.api.smartthings.com/device/list)
   * Click on `New Device`
   * Give it a `Name`, `Device Network Id` and select the new SmartDevice from the list.
       * The `Device Network Id` just needs to be a unique ID on the network, I went with `12345`
   * Click Create

Create the SmartApp
-------------------

   * Go to the ST IDE and head over to [`My SmartApps`](https://graph.api.smartthings.com/ide/apps)
   * Click on `New SmartApp`
   * Select `From Code`
   * Paste in the code from `glance.app.groovy`
   * Save and Publish this

Install the SmartApp
--------------------

   * From the SmartThings app, click the big `+`
   * Scroll over to `My Apps`
   * Install the new App
   * In the various lists, select all of your sensors
   * For the "`Thing`", select the new Device that you added in the beginning

Note
----

The app isn't defensive against `null` objects, so if you run into any please let me know.
