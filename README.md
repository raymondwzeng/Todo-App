# Project 1 - *Boring Todo* 📝

**Boring Todo** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Raymond Zeng**

Time spent: **4** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **view a list of todo items**
* [x] User can **successfully add and remove items** from the todo list
* [x] User's **list of items persisted** upon modification and and retrieved properly on app restart

The following **additional** features are planned, but not implemented:

* [ ] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list
* [ ] UI overhaul to be user-friendly with modern, bezel-less smartphones.
* [ ] A proper app icon

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://i.imgur.com/EA6xrvZ.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [EZGif's video to GIF](https://ezgif.com/video-to-gif) as well as OBS.

## Notes/Challenges
The main hurdle came from minor changes between video guides and the current Android Studio. Some methods are used or recommended more than others now, some options have been removed, and there appears to be a shift in how constraints are handled in-studio.

A (non-exhaustive) List of various tweaks:
- Removal of legacy Android settings (they are now enabled by default)
- Modifications to constraint system (instead of the tutorial, constrain elements on both sides, and set to `wrap_content` rather than `match_parent`
- Changes to how **ItemAdapter** changes are notified (use `notifyItemInserted()` on insertion rather than what video guide recommends)
- [Optional] Update to Apache Commons 2.8.0


## License

    Copyright [2021] [Raymond Zeng]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
