import axios from 'axios';

import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

export const ACTION_TYPES = {
  GET_EVENTS: 'events/GET_EVENTS',
};

const initialState = {
  loading: false,
  errorMessage: null,
  events: {} as any,
};

export type EventsState = Readonly<typeof initialState>;

// Reducer

export default (state: EventsState = initialState, action): EventsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.GET_EVENTS):
      return {
        ...state,
        errorMessage: null,
        loading: true
      };
    case FAILURE(ACTION_TYPES.GET_EVENTS):
      return {
        ...state,
        loading: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.GET_EVENTS):
      return {
        ...state,
        loading: false,
        events: action.payload.data
      };
    default:
      return state;
  }
};

// Actions

export const getEvents = () => ({
  type: ACTION_TYPES.GET_EVENTS,
  payload: axios.get('api/events')
});
