## Testing Web Services Locally using Android Emulator / connected device (usb) ##


**Problem:** Emulator cannot connect to local server running on the machine
using http://localhost

**Reason:** localhost or loop back will refer to the same system which is in our case the Emulator not the laptop.. But we want to refer to the local host on the laptop

**Solution:** Emulator refers to the machine it is running on with **IP: 10.0.2.2** so instead of using http://localhost use http://10.0.2.2 (Emulator)

> if your testing from ur device connected using usb use 10.0.1.2