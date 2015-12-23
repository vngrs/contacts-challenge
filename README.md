# VNGRS Contacts Challenge

This repo contains test environment of the VNGRS Contacts Challenge.
You don't need to use this tools to code, compile or test your project however we will.
So this step is completely optional but recommended.

You can find:

* A Vagrant file which will be used to create test enviroment ([more info][vagrant])
* A sample XML file that will be used to test the challenge.

**PS:** A much bigger XML file also will be used for testing.

## Setup

All you need to do is cloning the repo and provisioning the vagrant;

```sh
$ git clone https://github.com/vngrs/contacts-challenge.git vngrs-contacts-challenge
$ cd vngrs-contacts-challenge
$ vagrant up
```

After that you can connect VM with;

```sh
$ vagrant ssh
```

Remember all files in `vngrs-contacts-challenge` folder will be synchronized with the VM's `\vagrant` folder.

[vagrant]: <https://www.vagrantup.com/>

